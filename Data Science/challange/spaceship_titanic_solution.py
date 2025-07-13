# Spaceship Titanic - Kaggle Competition Solution
# Data Science Aufgabe 4

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.model_selection import train_test_split, cross_val_score, GridSearchCV, RandomizedSearchCV
from sklearn.ensemble import RandomForestClassifier, GradientBoostingClassifier
from sklearn.tree import DecisionTreeClassifier
from sklearn.linear_model import LogisticRegression
from sklearn.preprocessing import StandardScaler, LabelEncoder
from sklearn.metrics import accuracy_score, classification_report, confusion_matrix
from sklearn.impute import SimpleImputer
import warnings

warnings.filterwarnings("ignore")

# Für bessere Plots
plt.style.use("seaborn-v0_8")
sns.set_palette("husl")

print("🚀 Spaceship Titanic - Kaggle Competition")
print("=" * 50)

# ============================================================================
# 1. DATENEXPLORATION UND -REINIGUNG
# ============================================================================

print("\n📊 1. DATENEXPLORATION UND -REINIGUNG")
print("-" * 40)

# Daten laden
train_df = pd.read_csv("train.csv")
test_df = pd.read_csv("test.csv")

print(f"Training Set: {train_df.shape}")
print(f"Test Set: {test_df.shape}")

# Grundlegende Informationen
print("\n🔍 Erste 5 Zeilen des Trainingsdatensatzes:")
print(train_df.head())

print("\n📈 Datentypen und fehlende Werte:")
print(train_df.info())

print("\n🎯 Zielvariable - Verteilung von 'Transported':")
transported_counts = train_df["Transported"].value_counts()
print(transported_counts)
print(f"Anteil transportiert: {transported_counts[True] / len(train_df):.2%}")

# Für die Abgabe:
# Zielvariable analysieren: Ich prüfe zuerst die Balance der Zielvariable. 
# Falls die Klassen stark unbalanciert wären (z.B. 90% vs 10%), müsste ich 
# spezielle Techniken verwenden (SMOTE, Klassengewichtung). 
# Hier ist es fast 50/50 - perfekt für Standard-Algorithmen!

# Fehlende Werte analysieren
print("\n🔍 Fehlende Werte im Trainingsdatensatz:")
missing_train = train_df.isnull().sum()
missing_train = missing_train[missing_train > 0].sort_values(ascending=False)
print(missing_train)

print("\n🔍 Fehlende Werte im Testdatensatz:")
missing_test = test_df.isnull().sum()
missing_test = missing_test[missing_test > 0].sort_values(ascending=False)
print(missing_test)

# Für die Abgabe:
# Analyse fehlender Werte, um zu verstehen, ob sie zufällig oder systematisch sind

# Visualisierung der fehlenden Werte
fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(15, 6))

# Heatmap fehlende Werte
sns.heatmap(train_df.isnull(), cbar=True, ax=ax1, cmap="viridis")
ax1.set_title("Fehlende Werte - Training Set")

# Zielvariable
sns.countplot(data=train_df, x="Transported", ax=ax2)
ax2.set_title("Verteilung der Zielvariable")
plt.tight_layout()

# Plot als Datei speichern
plt.savefig("01_data_exploration.png", dpi=300, bbox_inches="tight")
print("📊 Plot gespeichert: 01_data_exploration.png")

# ============================================================================
# 2. FEATURE ENGINEERING
# ============================================================================

print("\n🔧 2. FEATURE ENGINEERING")
print("-" * 40)


def feature_engineering(df):
	"""Feature Engineering Pipeline"""
	df = df.copy()
	
	# 1. Cabin aufteilen (Format: "deck/num/side")
	df[["Deck", "Cabin_num", "Side"]] = df["Cabin"].str.split("/", expand=True)
	
	# Für die Abgabe:
	# Cabin aufteilen: "A/23/P".
	# Deck "A" könnte auf die reise Klasse hinweisen (höhere Decks = bessere Kabinen).
	# Side "P" (Port) vs "S" (Starboard) könnte bei Evakuierung relevant sein.
	# Cabin_num zeigt Position im Schiff
	
	# 2. Familiengruppen aus PassengerId (Format: "gggg_pp")
	df["Group"] = df["PassengerId"].str.split("_").str[0]
	df["Group_size"] = df.groupby("Group")["Group"].transform("count")
	
	# Für die Abgabe:
	# Familiengruppen: Format "0001_01" → 0001 = Gruppe, 01 = Person in Gruppe.
	# Familen haben andere überlebenschancen wie Einzelreisende.
	# Familien bleiben zusammen (Gruppen größe wichtig)
	
	# 3. Name Features
	df["First_name"] = df["Name"].str.split().str[0]
	df["Last_name"] = df["Name"].str.split().str[-1]
	df["Family_size"] = df.groupby("Last_name")["Last_name"].transform("count")
	
	# 4. Ausgaben-Features
	expenditure_cols = ["RoomService", "FoodCourt", "ShoppingMall", "Spa", "VRDeck"]
	df["Total_expenditure"] = df[expenditure_cols].sum(axis=1)
	df["Has_expenditure"] = (df["Total_expenditure"] > 0).astype(int)
	df["Expenditure_per_service"] = df["Total_expenditure"] / 5
	
	# Für die Abgabe:
	# Total_expenditure: Summe aller Ausgaben
	# Has_expenditure: 1 wenn Ausgaben > 0, sonst 0.
	# Expenditure_per_service: Durchschnittliche Ausgaben pro Service.
	# Mehr ausgaben = eventuell mehr Komfort und bessere Überlebenschancen
	
	# 5. Altersgruppen
	df["Age_group"] = pd.cut(df["Age"], bins=[0, 12, 18, 35, 50, 100], labels=["Child", "Teen", "Young_Adult", "Adult", "Senior"])
	
	# Für die Abgabe:
	# Aufteilung in Altersgruppen (Child, Teen, Young_Adult, Adult, Senior):
	# Kinder in der regele mit eltern unterwegs
	# Senioren sind langsam
	# Teenager eventuell allein oder noch mit den Eltern unterwegs
	
	# 6. Boolean Features richtig konvertieren
	boolean_cols = ["CryoSleep", "VIP"]
	for col in boolean_cols:
		df[col] = df[col].map({"True": 1, "False": 0})
	
	return df


# Feature Engineering anwenden
train_processed = feature_engineering(train_df)
test_processed = feature_engineering(test_df)

print("✅ Feature Engineering abgeschlossen!")
print(f"Neue Features erstellt. Trainingsdaten: {train_processed.shape}")

# Neue Features anzeigen
new_features = ["Deck", "Cabin_num", "Side", "Group_size", "Family_size", "Total_expenditure", "Has_expenditure", "Age_group"]
print("\n📊 Neue Features - Beispiele:")
print(train_processed[new_features].head())

# ============================================================================
# 3. DATENBEREINIGUNG UND PREPROCESSING
# ============================================================================

print("\n🧹 3. DATENBEREINIGUNG UND PREPROCESSING")
print("-" * 40)


def preprocess_data(df, is_train=True):
	"""Preprocessing Pipeline"""
	df = df.copy()
	
	# Features für das Modell auswählen
	feature_cols = [
		"HomePlanet", "CryoSleep", "Deck", "Side", "Destination",
		"Age", "VIP", "RoomService", "FoodCourt", "ShoppingMall",
		"Spa", "VRDeck", "Group_size", "Family_size",
		"Total_expenditure", "Has_expenditure"
	]
	
	# Kategorische Features
	categorical_cols = ["HomePlanet", "Deck", "Side", "Destination"]
	
	# Numerische Features
	numerical_cols = [
		"Age", "RoomService", "FoodCourt", "ShoppingMall",
		"Spa", "VRDeck", "Group_size", "Family_size", "Total_expenditure"
	]
	
	# Boolean Features
	boolean_cols = ["CryoSleep", "VIP", "Has_expenditure"]
	
	# Fehlende Werte füllen
	# Kategorische Features: Modus
	for col in categorical_cols:
		mode_val = df[col].mode().iloc[0] if len(df[col].mode()) > 0 else "Unknown" # Für die Abgabe: Füllen mit dem häufigsten Wert oder Unknown
		df[col] = df[col].fillna(mode_val)
	
	# Numerische Features: Median
	for col in numerical_cols:
		median_val = df[col].median()
		df[col] = df[col].fillna(median_val)
	
	# Für die Abgabe:
	# Füllen mit dem Median ist robuster gegen Ausreißer
	
	# Boolean Features: 0
	for col in boolean_cols:
		df[col] = df[col].fillna(0)
	
	# Für die Abgabe:
	# Boolean immer mit false füllen da hier bester guess
	# Im nachhinein, füllen basierend auf der
	
	# One-Hot Encoding für kategorische Features
	df_encoded = pd.get_dummies(df[feature_cols], columns=categorical_cols, drop_first=True)
	
	# Für die Abgabe:
	# One-Hot Encoding: "Earth", "Mars", "Europa" sind nominale Kategorien ohne
	# natürliche Reihenfolge. ML-Algorithmen brauchen Zahlen, nicht Text.
	# drop_first=True vermeidet Multikollinearität (dummy variable trap).
	
	return df_encoded


# Preprocessing anwenden
X_processed = preprocess_data(train_processed)
X_test_processed = preprocess_data(test_processed)

# Zielvariable
y = train_processed["Transported"].astype(int)

print(f"✅ Preprocessing abgeschlossen!")
print(f"Features nach Preprocessing: {X_processed.shape[1]}")
print(f"Feature-Namen: {list(X_processed.columns[:10])}...")  # Erste 10 zeigen

# ============================================================================
# 4. MODELLAUSWAHL UND -BEWERTUNG
# ============================================================================

print("\n🤖 4. MODELLAUSWAHL UND -BEWERTUNG")
print("-" * 40)

# Train-Validation Split
X_train, X_val, y_train, y_val = train_test_split(X_processed, y, test_size=0.2, random_state=42, stratify=y)

print(f"Training Set: {X_train.shape}")
print(f"Validation Set: {X_val.shape}")

# Für die Abgabe:
# Train/Validation Split (80/20): 80/20 ist Standard-Verhältnis für genug Train- und
# Test-Daten. stratify=y sorgt für gleiche Klassenverteilung in Train und Validation.
# random_state=42 überall für Reproduzierbarkeit - gleiche Ergebnisse bei jedem Lauf.

# Modelle definieren
models = {
	"Decision Tree": DecisionTreeClassifier(random_state=42),
	"Random Forest": RandomForestClassifier(random_state=42, n_estimators=100),
	"Logistic Regression": LogisticRegression(random_state=42, max_iter=1000),
	"Gradient Boosting": GradientBoostingClassifier(random_state=42)
}

# Für die Abgabe:
# Warum diese 4 Algorithmen?
# Decision Tree: Sehr interpretierbar ("Wenn Age < 18 UND HomePlanet = Mars → Transported"),
# schnell zu trainieren, keine Skalierung nötig. ABER: Overfitting-anfällig und instabil.
#
# Random Forest: Ensemble aus vielen Decision Trees, weniger Overfitting durch Averaging,
# robuster und meist bessere Performance, Feature Importance verfügbar.
#
# Logistic Regression: Baseline-Modell - einfach und interpretierbar, probabilistische
# Ausgabe (Wahrscheinlichkeiten), gut bei linearen Beziehungen.
#
# Gradient Boosting: Sequenzielle Verbesserung - jeder Baum korrigiert Fehler des
# vorherigen, oft beste Performance bei strukturierten Daten, Kaggle-Gewinner-Algorithmus.

# Modelle trainieren und evaluieren
model_results = {}

print("\n📊 Modell-Performance (Cross-Validation):")
print("-" * 50)

for name, model in models.items():
	# Cross-Validation
	cv_scores = cross_val_score(model, X_train, y_train, cv=5, scoring="accuracy")
	
	# Für die Abgabe:
	# Cross-Validation (5-fold): Robuste Evaluation - nicht nur ein zufälliger
	# Train/Test Split. 5 Folds = guter Kompromiss zwischen Genauigkeit und Rechenzeit.
	# Verhindert Glück bei der Datenaufteilung.
	
	# Training auf vollem Trainingsdatensatz
	model.fit(X_train, y_train)
	
	# Validation Accuracy
	val_pred = model.predict(X_val)
	val_accuracy = accuracy_score(y_val, val_pred)
	
	model_results[name] = {
		"model": model,
		"cv_mean": cv_scores.mean(),
		"cv_std": cv_scores.std(),
		"val_accuracy": val_accuracy
	}
	
	print(f"{name:20s} | CV: {cv_scores.mean():.4f} (±{cv_scores.std():.4f}) | Val: {val_accuracy:.4f}")

# Bestes Modell auswählen
best_model_name = max(model_results.keys(), key=lambda x: model_results[x]["cv_mean"])
best_model = model_results[best_model_name]["model"]

print(f"\n🏆 Bestes Modell: {best_model_name}")
print(f"Cross-Validation Accuracy: {model_results[best_model_name]['cv_mean']:.4f}")

# ============================================================================
# 5. HYPERPARAMETER-TUNING
# ============================================================================

print("\n⚙️ 5. HYPERPARAMETER-TUNING")
print("-" * 40)

# Parameter-Grid für das beste Modell definieren
if best_model_name == 'Random Forest':
	param_grid = {
		"n_estimators": [100, 200, 300],
		"max_depth": [10, 20, None],
		"min_samples_split": [2, 5, 10],
		"min_samples_leaf": [1, 2, 4]
	}
# Für die Abgabe:
# Random Forest Parameter:
# n_estimators (Anzahl Bäume): 100=schnell aber vielleicht underfitting,
# 200=Balance zwischen Speed und Performance, 300=mehr Bäume=bessere Performance aber langsamer.
# max_depth: 10=verhindert Overfitting, 20=mehr Komplexität erlaubt, None=vollständige Bäume.
# min_samples_split/leaf: Kleinere Werte=mehr Splits möglich→komplexere Modelle,
# größere Werte=konservativere Splits→weniger Overfitting.

elif best_model_name == "Decision Tree":
	param_grid = {
		"max_depth": [5, 10, 20, None],
		"min_samples_split": [2, 5, 10, 20],
		"min_samples_leaf": [1, 2, 4, 8]
	}
elif best_model_name == "Logistic Regression":
	param_grid = {
		"C": [0.1, 1, 10, 100],
		"solver": ["liblinear", "lbfgs"]
	}
else:  # Gradient Boosting
	param_grid = {
		"n_estimators": [100, 200],
		"max_depth": [3, 5, 7],
		"learning_rate": [0.1, 0.01]
	}

print(f"🔍 Hyperparameter-Tuning für {best_model_name}...")

# Grid Search mit Cross-Validation
grid_search = GridSearchCV(
	models[best_model_name],
	param_grid,
	cv=5,
	scoring="accuracy",
	n_jobs=-1,
	verbose=1
)

# Für die Abgabe:
# Grid Search statt Random Guessing: Systematische Optimierung aller Parameter-Kombinationen.
# cv=5 für robuste Evaluation während Tuning. n_jobs=-1 nutzt alle CPU-Kerne.

grid_search.fit(X_train, y_train)

print(f"✅ Hyperparameter-Tuning abgeschlossen!")
print(f"Beste Parameter: {grid_search.best_params_}")
print(f"Beste CV-Score: {grid_search.best_score_:.4f}")

# Finales Modell
final_model = grid_search.best_estimator_

# Finale Evaluation
final_val_pred = final_model.predict(X_val)
final_val_accuracy = accuracy_score(y_val, final_val_pred)

print(f"Finale Validation Accuracy: {final_val_accuracy:.4f}")

# ============================================================================
# 6. MODELLANALYSE UND FEATURE IMPORTANCE
# ============================================================================

print("\n📊 6. MODELLANALYSE")
print("-" * 40)

# Classification Report
print("Classification Report (Validation Set):")
print(classification_report(y_val, final_val_pred))

# Für die Abgabe:
# Classification Report: Mehr als nur Accuracy.
# Precision: Von allen "Transported"-Vorhersagen, wie viele waren richtig?
# Recall: Von allen echten "Transported", wie viele haben wir gefunden?
# F1-Score: Harmonisches Mittel von Precision und Recall.

# Confusion Matrix
plt.figure(figsize=(12, 5))

plt.subplot(1, 2, 1)
cm = confusion_matrix(y_val, final_val_pred)
sns.heatmap(cm, annot=True, fmt="d", cmap="Blues")
plt.title("Confusion Matrix")
plt.ylabel("True Label")
plt.xlabel("Predicted Label")

# Für die Abgabe:
# Confusion Matrix zeigt WO das Modell Fehler macht:
# False Positive: Dachten "transported", war aber nicht.
# False Negative: Dachten "nicht transported", war aber doch.

# Feature Importance (falls verfügbar)
if hasattr(final_model, "feature_importances_"):
	plt.subplot(1, 2, 2)
	feature_importance = pd.DataFrame({
		"feature": X_processed.columns,
		"importance": final_model.feature_importances_
	}).sort_values("importance", ascending=False).head(10)
	
	sns.barplot(data=feature_importance, y="feature", x="importance")
	plt.title("Top 10 Feature Importance")
	plt.xlabel("Importance")

# Für die Abgabe:
# Feature Importance: Verstehen welche Features wichtig sind, Domain Knowledge
# validieren (macht Sinn was wichtig ist?), Feature Selection für zukünftige Modelle.

plt.tight_layout()

# Plot als Datei speichern
plt.savefig("02_model_analysis.png", dpi=300, bbox_inches="tight")
print("📊 Plot gespeichert: 02_model_analysis.png")

if hasattr(final_model, "feature_importances_"):
	print("\n🎯 Top 10 wichtigste Features:")
	for i, (feature, importance) in enumerate(feature_importance.head(10).values, 1):
		print(f"{i:2d}. {feature:25s} | {importance:.4f}")

# ============================================================================
# 7. VORHERSAGEN FÜR KAGGLE SUBMISSION
# ============================================================================

print("\n🎯 7. VORHERSAGEN FÜR KAGGLE SUBMISSION")
print("-" * 40)

# Modell auf kompletten Trainingsdaten trainieren
print("Training des finalen Modells auf allen Trainingsdaten...")
final_model.fit(X_processed, y)

# Für die Abgabe:
# Final Training auf allen Daten: Mehr Daten = besseres Modell für finale Vorhersagen.
# Hyperparameter bereits auf Validation Set optimiert. Kaggle Submission braucht
# beste mögliche Performance, daher nutzen wir alle verfügbaren Trainingsdaten.

# Vorhersagen für Testdaten
print("Erstelle Vorhersagen für Testdaten...")
test_predictions = final_model.predict(X_test_processed)

# Submission DataFrame erstellen
submission_df = pd.DataFrame({
	"PassengerId": test_df["PassengerId"],
	"Transported": test_predictions.astype(bool)
})

# Für die Abgabe:
# Kaggle-spezifisches Format: Exakt das Format was Kaggle erwartet (Boolean, nicht 0/1).
# Pipeline Thinking: Gleiche Transformationen für Train und Test durch unsere Funktionen.

print(f"✅ Vorhersagen erstellt für {len(submission_df)} Passagiere")
print(f"Anteil als 'transportiert' vorhergesagt: {test_predictions.mean():.2%}")

# Submission Datei speichern
submission_df.to_csv("spaceship_titanic_submission.csv", index=False)
print("💾 Submission Datei gespeichert: 'spaceship_titanic_submission.csv'")

# Erste 10 Vorhersagen anzeigen
print("\n🔍 Erste 10 Vorhersagen:")
print(submission_df.head(10))

# ============================================================================
# 8. ZUSAMMENFASSUNG
# ============================================================================

print("\n" + "=" * 60)
print("📋 ZUSAMMENFASSUNG")
print("=" * 60)

print(f"""
🎯 AUFGABE: Kaggle Spaceship Titanic Competition
📊 DATEN: {len(train_df)} Training + {len(test_df)} Test Samples
🔧 FEATURES: {X_processed.shape[1]} nach Feature Engineering
🤖 BESTES MODELL: {best_model_name}
⚙️ HYPERPARAMETER: Grid Search optimiert
📈 PERFORMANCE: {final_val_accuracy:.4f} Validation Accuracy
💾 OUTPUT: spaceship_titanic_submission.csv

🚀 NÄCHSTE SCHRITTE:
1. Upload der Submission-Datei auf Kaggle
2. Optional: Weitere Feature Engineering Experimente
3. Optional: Ensemble-Methoden ausprobieren
""")

print("✅ Analyse abgeschlossen! Viel Erfolg bei der Competition! 🚀")

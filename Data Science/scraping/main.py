# Solar Flare Data Scraping and Analysis
# Data Science - Task 1
# Luis Staudt & Ismael David Quinonero Angel

import os
import re
from datetime import datetime
from io import StringIO

import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import cloudscraper  # Using cloud scraper instead of requests
from bs4 import BeautifulSoup

pd.set_option("display.max_columns", None)
pd.set_option("display.expand_frame_repr", False)


# Part 1: Data Scraping and Cleaning
## Step 1: Data Scraping and Preparation
def scrape_spaceweatherlive(html_file_path="spaceweatherlive.html"):
	"""
	Scrapes the top 50 solar flares data from SpaceWeatherLive.com
	Returns a pandas DataFrame with the scraped data
	"""
	
	print("Scraping data from SpaceWeatherLive.com...")
	
	url = "https://www.spaceweatherlive.com/en/solar-activity/top-50-solar-flares.html"
	headers = {
		"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:137.0) Gecko/20100101 Firefox/137.0",
	}
	
	use_backup = False
	scraper = cloudscraper.create_scraper()  # Create a cloudscraper instance
	
	try:
		response = scraper.get(url, headers=headers)
		if response.status_code != 200:
			print(f"Failed to retrieve data: Status code {response.status_code}")
			use_backup = True
		else:
			html_content = response.text
	except Exception as e:
		print(f"Error during web scraping: {e}")
		use_backup = True
	
	if use_backup:
		print(f"Attempting to read from HTML file: {html_file_path}")
		if os.path.exists(html_file_path):
			with open(html_file_path, "r", encoding="utf-8") as file:
				html_content = file.read()
			print("Successfully loaded HTML file")
		else:
			print(f"HTML file not found at {html_file_path}")
			return None
	
	soup = BeautifulSoup(html_content, "html.parser")
	table = soup.find("table", class_="table")
	
	if table is None:
		print("Table not found, searching for alternative...")
		tables = soup.find_all("table")
		if tables:
			table = tables[0]
		else:
			print("No tables found in the HTML")
			return None
	
	try:
		dfs = pd.read_html(StringIO(str(table)))
		
		if not dfs:
			print("Failed to parse table")
			return None
		
		df = dfs[0]
		df.columns = ["rank", "x_class", "date", "region", "start_time", "max_time", "end_time", "archive"]
		
		print("Data parsing successful!")
		return df
	
	except Exception as e:
		print(f"Error parsing HTML table: {e}")
		return None


spaceweather_df = scrape_spaceweatherlive()
if spaceweather_df is None:
	raise Exception("Failed to scrape SpaceWeatherLive data!")

print("\nSpaceWeatherLive Top 50 Solar Flares Data:")
print(spaceweather_df.head(n=50))


# Step 2: Clean the Top 50 Solar Flare Data
def clean_spaceweather_data(df):
	"""
	Cleans the SpaceWeatherLive data:
	1. Removes the 'archive' column
	2. Combines date and time columns into datetime objects
	3. Replaces '-' regions with NaN
	"""
	
	print("Cleaning SpaceWeatherLive data...")
	
	cleaned_df = df.copy()
	cleaned_df = cleaned_df.drop(columns=["archive"])
	
	for time_col in ["start_time", "max_time", "end_time"]:
		new_col = time_col.replace("_time", "_datetime")
		cleaned_df[new_col] = None
		
		for idx, row in cleaned_df.iterrows():
			date_str = row["date"]
			time_str = row[time_col]
			
			try:
				dt = datetime.strptime(f"{date_str} {time_str}", "%Y/%m/%d %H:%M")
				cleaned_df.at[idx, new_col] = dt
			except ValueError as e:
				print(f"Error parsing datetime at row {idx}: {e}")
				cleaned_df.at[idx, new_col] = None
	
	cleaned_df["region"] = cleaned_df["region"].replace("-", np.nan)
	cleaned_df = cleaned_df.drop(columns=["start_time", "max_time", "end_time", "date"])
	cleaned_df = cleaned_df[["rank", "x_class", "start_datetime", "max_datetime", "end_datetime", "region"]] # Reorder columns
	
	print("Data cleaning completed!")
	return cleaned_df


cleaned_spaceweather_df = clean_spaceweather_data(spaceweather_df)
print("\nCleaned SpaceWeatherLive Data:")
print(cleaned_spaceweather_df.head(n=50))


## Step 3: Scrape NASA data
def scrape_nasa_data():
	"""
	Scrapes solar flare data from NASA's website
	Returns a pandas DataFrame with the scraped data
	"""
	
	print("\nScraping data from NASA website...")
	url = "https://cdaw.gsfc.nasa.gov/CME_list/radio/waves_type2.html"
	alt_url = "http://www.hcbravo.org/IntroDataSci/misc/waves_type2.html"
	headers = {
		"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:137.0) Gecko/20100101 Firefox/137.0"
	}
	
	scraper = cloudscraper.create_scraper()  # Create a cloudscraper instance
	try:
		response = scraper.get(url, headers=headers, timeout=10)
		if response.status_code != 200:
			print(f"Primary URL failed with status {response.status_code}, trying alternative...")
			response = scraper.get(alt_url, headers=headers, timeout=10)
	except Exception as e:
		print("Primary URL request failed, trying alternative...")
		response = scraper.get(alt_url, headers=headers, timeout=10)
	
	if response.status_code != 200:
		print(f"Failed to retrieve data: Status code {response.status_code}")
		return None
	
	soup = BeautifulSoup(response.text, "html.parser")
	pre_element = soup.find("pre")
	
	if pre_element is None:
		print("Pre element not found, trying alternative approach...")
		tables = soup.find_all("table")
		if tables:
			print(f"Found {len(tables)} tables")
			table = max(tables, key=lambda t: len(t.find_all("tr")))
			rows = table.find_all("tr")
			data = []
			for row in rows[1:]:  # Skip header row
				cols = row.find_all(["td", "th"])
				if len(cols) >= 14:  # Ensure there are enough columns
					row_data = [col.get_text().strip() for col in cols]
					data.append(row_data)
		else:
			print("No tables found on the page")
			return None
	else:
		text = pre_element.get_text()
		lines = text.split("\n")
		
		start_idx = 0
		for i, line in enumerate(lines):  # Search for line where data starts
			if re.match(r"\s*\d{4}/\d{2}/\d{2}\s+", line):
				start_idx = i
				break
		
		data = []
		for line in lines[start_idx:]:  # Process lines after the header
			if not line.strip() or not re.match(r"\s*\d{4}/\d{2}/\d{2}\s+", line):
				continue
			
			fields = []
			
			# Parses date and time
			date_match = re.search(r"(\d{4}/\d{2}/\d{2})", line)
			if date_match:
				fields.append(date_match.group(1))
			else:
				continue
			
			# Parses all other columns, splitting by whitespace
			remaining = line[date_match.end():].strip()
			fields.extend(re.split(r"\s+", remaining)[:13])
			
			while len(fields) < 14:
				fields.append("")
			
			data.append(fields)
	
	columns = [
		"start_date", "start_time", "end_date", "end_time",
		"start_frequency", "end_frequency", "flare_location", "flare_region",
		"flare_classification", "cme_date", "cme_time", "cme_angle",
		"cme_width", "cme_speed"
	]
	
	df = pd.DataFrame(data, columns=columns)
	
	print("NASA data scraping successful!")
	return df


nasa_df = scrape_nasa_data()
if nasa_df is None:
	raise Exception("Failed to scrape NASA data!")

print("\nNASA Solar Flare Data:")
print(nasa_df.head(n=50))


## Step 4: Clean the NASA table
def clean_nasa_data(df):
	"""
	Cleans the NASA data:
	1. Codes missing entries as NaN
	2. Handles special cases in the cme_angle column (Halo events)
	3. Processes the width column to handle lower bounds
	4. Combines date and time fields into datetime objects
	"""
	
	print("\nCleaning NASA data...")
	
	cleaned_df = df.copy()
	cleaned_df = cleaned_df.replace("----", np.nan).replace("-----", np.nan).replace("------", np.nan)
	
	cleaned_df["is_halo"] = cleaned_df["cme_angle"] == "Halo"
	cleaned_df["cpa"] = cleaned_df["cme_angle"].replace("Halo", np.nan)
	cleaned_df["cpa"] = pd.to_numeric(cleaned_df["cpa"], errors="coerce")
	cleaned_df["width_lower_bound"] = cleaned_df["cme_width"].str.startswith(">")
	cleaned_df["width"] = cleaned_df["cme_width"].str.replace(">", "", regex=False)
	cleaned_df["width"] = pd.to_numeric(cleaned_df["width"], errors="coerce")
	cleaned_df["speed"] = pd.to_numeric(cleaned_df["cme_speed"], errors="coerce")
	
	cleaned_df["start_datetime"] = None
	for idx, row in cleaned_df.iterrows():
		try:
			if pd.notna(row["start_date"]) and pd.notna(row["start_time"]):
				dt = datetime.strptime(f"{row['start_date']} {row['start_time']}", "%Y/%m/%d %H:%M")
				cleaned_df.at[idx, "start_datetime"] = dt
		except (ValueError, TypeError) as e:
			print(f"Error parsing start datetime at row {idx}: {e}")
			cleaned_df.at[idx, "start_datetime"] = None
	
	cleaned_df["end_datetime"] = None
	for idx, row in cleaned_df.iterrows():
		try:
			if pd.notna(row["end_date"]) and pd.notna(row["end_time"]):
				year = str(row["start_date"]).split("/")[0] if pd.notna(row["start_date"]) else "2025"
				
				# Fix for cases where time is "24:00"
				end_time = row["end_time"]
				if end_time == "24:00":
					end_time = "23:59"
				
				dt = datetime.strptime(f"{year}/{row['end_date']} {end_time}", "%Y/%m/%d %H:%M")
				
				cleaned_df.at[idx, "end_datetime"] = dt
		except (ValueError, TypeError, AttributeError) as e:
			print(f"Error parsing end datetime at row {idx}: {e}")
			cleaned_df.at[idx, "end_datetime"] = None
	
	cleaned_df["cme_datetime"] = None
	for idx, row in cleaned_df.iterrows():
		try:
			if pd.notna(row["cme_date"]) and pd.notna(row["cme_time"]):
				if "--/--" in str(row["cme_date"]) or "--:--" in str(row["cme_time"]):
					continue
				
				year = str(row["start_date"]).split("/")[0] if pd.notna(row["start_date"]) else "2025"
				dt = datetime.strptime(f"{year}/{row['cme_date']} {row['cme_time']}", "%Y/%m/%d %H:%M")
				
				cleaned_df.at[idx, "cme_datetime"] = dt
		except (ValueError, TypeError, AttributeError) as e:
			cleaned_df.at[idx, "cme_datetime"] = None
	
	cleaned_df["start_frequency"] = pd.to_numeric(cleaned_df["start_frequency"], errors="coerce")
	cleaned_df["end_frequency"] = pd.to_numeric(cleaned_df["end_frequency"], errors="coerce")
	
	cleaned_df["plot"] = "PHTX"
	
	final_columns = [
		"start_datetime", "end_datetime", "start_frequency", "end_frequency",
		"flare_location", "flare_region", "flare_classification",
		"cme_datetime", "cpa", 'width', "speed", "plot", "is_halo", "width_lower_bound"
	]
	
	cleaned_df = cleaned_df[final_columns]
	print("NASA data cleaning completed!")
	return cleaned_df


cleaned_nasa_df = clean_nasa_data(nasa_df)
print("\nCleaned NASA Data:")
print(cleaned_nasa_df.head(n=50))


# Part 2: Analysis
## Question 1: Replication
def analyze_nasa_top_flares(df):
	"""
	Identifies the top 50 solar flares from the NASA data based on X-classification.
	Returns a DataFrame with these top flares.
	"""
	
	print("\nAnalyzing top flares from NASA data...")
	nasa_copy = df.copy()
	# Filter for X-class flares
	x_flares = nasa_copy[nasa_copy["flare_classification"].str.startswith("X", na=False)].copy()
	
	if len(x_flares) == 0:
		print("No X-class flares found in NASA data")
		return None
	
	x_flares["x_value"] = x_flares["flare_classification"].str.extract(r"X(\d+\.?\d*)", expand=False).astype(float)
	top_flares = x_flares.sort_values("x_value", ascending=False).head(50)
	top_flares["rank"] = range(1, len(top_flares) + 1)
	
	print(f"Found {len(top_flares)} X-class flares in the top 50")
	return top_flares


# Get the top 50 flares from the NASA data
nasa_top_flares = analyze_nasa_top_flares(cleaned_nasa_df)
print("\nTop 50 Flares:")
print(nasa_top_flares.head(n=50))


# Compare with SpaceWeatherLive data
def compare_top_flares(spaceweather_df, nasa_top_flares):
	"""
	Compares the top 50 flares from SpaceWeatherLive with those identified from NASA data.
	Returns a summary of the comparison.
	"""
	
	if nasa_top_flares is None or spaceweather_df is None:
		return "Unable to compare due to missing data"
	
	spaceweather_df["x_value"] = spaceweather_df["x_class"].str.extract(r"X(\d+\.?\d*)", expand=False).astype(float)
	rank_matches = sum(1 for i, row in spaceweather_df.iterrows() if i < len(nasa_top_flares) and abs(row["x_value"] - nasa_top_flares.iloc[i]["x_value"]) < 0.1)
	
	date_matches = 0
	for _, sw_row in spaceweather_df.iterrows():
		for _, nasa_row in nasa_top_flares.iterrows():
			# Match spaceweatherlive and NASA data based on date and X-class value
			if abs((sw_row["start_datetime"] - nasa_row["start_datetime"]).total_seconds()) < 86400 and abs(sw_row["x_value"] - nasa_row["x_value"]) < 0.1:
				date_matches += 1
				break
	
	return {
		"total_spaceweather": len(spaceweather_df),
		"total_nasa_top": len(nasa_top_flares),
		"rank_matches": rank_matches,
		"date_matches": date_matches,
		"rank_match_percentage": round(rank_matches / min(len(spaceweather_df), len(nasa_top_flares)) * 100, 2),
		"date_match_percentage": round(date_matches / min(len(spaceweather_df), len(nasa_top_flares)) * 100, 2)
	}


comparison_results = compare_top_flares(cleaned_spaceweather_df, nasa_top_flares)
print("\nComparison of Top Flares:")
print(comparison_results)


## Question 2: Integration
def find_best_matches(spaceweather_df, nasa_df):
	"""
	Finds the best matching row in NASA data for each of the top 50 flares from SpaceWeatherLive.
	Uses date, time, and X-class value for matching.
	Returns the NASA DataFrame with an added column indicating the SpaceWeatherLive rank.
	"""
	
	print("\nFinding best matches between datasets...")
	
	sw_df = spaceweather_df.copy()
	nasa_df = nasa_df.copy()
	nasa_df["spaceweatherlive_rank"] = np.nan
	
	for idx, sw_row in sw_df.iterrows():
		best_match_idx = None
		best_match_score = 0
		
		for nasa_idx, nasa_row in nasa_df.iterrows():
			if not isinstance(nasa_row["flare_classification"], str) or not nasa_row["flare_classification"].startswith("X"):
				continue
			
			nasa_x_value = float(nasa_row["flare_classification"].replace("X", ""))
			sw_x_value = float(sw_row["x_class"].replace("X", "").replace("+", ""))
			
			if pd.notna(sw_row["start_datetime"]) and pd.notna(nasa_row["start_datetime"]):
				time_diff = abs((sw_row["start_datetime"] - nasa_row["start_datetime"]).total_seconds())
				x_diff = abs(sw_x_value - nasa_x_value)
				
				# Generates a score to match the two rows
				# Experimenting with different weights for time and X-class value
				# This seems to work well for the data
				score = 1.0 / (1.0 * time_diff / 86400 + 10.0 * x_diff)
				
				if pd.notna(sw_row["region"]) and pd.notna(nasa_row["flare_region"]):
					if str(sw_row["region"]) == str(nasa_row["flare_region"]):
						score *= 1.5
				
				if score > best_match_score:
					best_match_score = score
					best_match_idx = nasa_idx
		
		if best_match_score > 0.01:
			nasa_df.at[best_match_idx, "spaceweatherlive_rank"] = sw_row["rank"]
	
	print("Matching completed!")
	return nasa_df


nasa_df_with_matches = find_best_matches(cleaned_spaceweather_df, cleaned_nasa_df)

matches_count = nasa_df_with_matches["spaceweatherlive_rank"].notna().sum()
print(f"\nFound matches for {matches_count} out of {len(cleaned_spaceweather_df)} SpaceWeatherLive flares")

print("\nSample of matches:")
matches_sample = nasa_df_with_matches[nasa_df_with_matches["spaceweatherlive_rank"].notna()].head(n=50)
print(matches_sample[["spaceweatherlive_rank", "start_datetime", "flare_classification", "flare_region"]])


# Halo-CMEs in den Top-Flakes vs. Alle Flares
#  Verglichen des Prozentsatz der Halo-CMEs in den Top 50 Flares mit dem gesamten Datensatz
#  Unsere Analyse zeigte, dass stärkere Flares (die in den Top 50) signifikant häufiger mit Halo-CMEs assoziiert sind
#  Dies deutet auf eine Korrelation zwischen der Flare-Intensität und der Wahrscheinlichkeit eines ausgedehnten koronalen Massenauswurfs hin

# Zeitliche Verteilung der Sonnenflares
#  Darstellung der monatlichen Sonnenflares über einen Zeitraum von 10 Jahren
#  Hervorhebung der Monate, in denen die Top 50 Flares aufgetreten sind
#  Die Visualisierung enthüllte klare Muster des Sonnenzyklus
#  Wir beobachteten, dass die stärksten Flares dazu neigen, sich zeitlich zu bündeln und oft in Zeiten erhöhter Sonnenaktivität auftreten

# Beziehung zwischen Flare-Stärke und CME-Geschwindigkeit
#  Analyse der Beziehung zwischen der Stärke der Sonnenflares (X-Klasse) und der Geschwindigkeit der koronalen Massenauswürfe (CMEs)
#  Stärkere Flares (höhere X-Klasse-Werte) werden tendenziell mit schnelleren CMEs in Verbindung gebracht (Trendlinie)

def analyze_flares(nasa_df):
	"""
	Analyzes the solar flare data to identify patterns related to the top 50 flares.
	Creates visualizations to show insights.
	"""
	
	print("\nAnalyzing solar flare data...")
	df = nasa_df.copy()
	df["is_top50"] = df["spaceweatherlive_rank"].notna()
	
	# 1. Analyze Halo CMEs in top 50 vs. all flares
	top50_halo_pct = df[df["is_top50"]]["is_halo"].mean() * 100
	all_halo_pct = df["is_halo"].mean() * 100
	
	print(f"\nPercentage of Halo CMEs in top 50 flares: {top50_halo_pct:.2f}%")
	print(f"Percentage of Halo CMEs in all flares: {all_halo_pct:.2f}%")
	
	plt.figure(figsize=(10, 6))
	categories = ["All Flares", "Top 50 Flares"]
	percentages = [all_halo_pct, top50_halo_pct]
	
	bars = plt.bar(categories, percentages, color=["skyblue", "orangered"])
	
	plt.title("Comparison of Halo CMEs in Top 50 Flares vs. All Flares", fontsize=14)
	plt.ylabel("Percentage with Halo CMEs", fontsize=12)
	plt.ylim(0, 100)
	
	# Add percentage labels on top of bars
	for bar, pct in zip(bars, percentages):
		plt.text(bar.get_x() + bar.get_width() / 2, bar.get_height() + 2, f"{pct:.1f}%", ha="center", fontsize=11)
	
	plt.grid(axis="y", linestyle="--", alpha=0.7)
	plt.savefig("halo_cme_comparison.png", dpi=300, bbox_inches="tight")
	
	# 2. Analyze temporal distribution of flares
	if not pd.api.types.is_datetime64_dtype(df["start_datetime"]):
		df["start_datetime"] = pd.to_datetime(df["start_datetime"])
	
	df["year"] = df["start_datetime"].dt.year
	df["month"] = df["start_datetime"].dt.month
	df["year_month"] = df["start_datetime"].dt.to_period('M')
	
	monthly_counts = df.groupby("year_month").size().reset_index(name="count")
	monthly_counts["date"] = monthly_counts["year_month"].dt.to_timestamp()
	top50_monthly = df[df["is_top50"]].groupby("year_month").size().reset_index(name="top50_count")
	
	monthly_data = pd.merge(monthly_counts, top50_monthly, on="year_month", how="left")
	monthly_data["top50_count"] = monthly_data["top50_count"].fillna(0)
	
	monthly_data = monthly_data.sort_values("date")
	plt.figure(figsize=(15, 7))
	plt.plot(monthly_data["date"], monthly_data["count"], color="skyblue", linewidth=2, label="All Flares")
	top50_months = monthly_data[monthly_data["top50_count"] > 0]
	
	scatter = plt.scatter(top50_months["date"], top50_months["count"], s=top50_months["top50_count"] * 50, color="red", alpha=0.6, label="Months with Top 50 Flares")
	
	plt.title("Monthly Solar Flare Activity (1997-2006)", fontsize=14)
	plt.xlabel("Year", fontsize=12)
	plt.ylabel("Number of Flares", fontsize=12)
	plt.grid(True, alpha=0.3)
	plt.legend(loc="upper left")
	plt.savefig("flare_temporal_distribution.png", dpi=300, bbox_inches="tight")
	
	# 3. Analyze the relationship between flare strength and CME speed
	speed_data = df[pd.notna(df["speed"]) & df["flare_classification"].str.startswith("X", na=False)].copy()
	speed_data["x_value"] = speed_data["flare_classification"].str.extract(r"X(\d+\.?\d*)", expand=False).astype(float)
	plt.figure(figsize=(12, 8))
	plt.scatter(speed_data[~speed_data["is_top50"]]["x_value"], speed_data[~speed_data["is_top50"]]["speed"], color="skyblue", alpha=0.6, label="Other X-class Flares")
	
	plt.scatter(speed_data[speed_data["is_top50"]]["x_value"], speed_data[speed_data["is_top50"]]["speed"], color="red", alpha=0.8, label="Top 50 Flares")
	
	if len(speed_data) > 1:
		z = np.polyfit(speed_data["x_value"], speed_data["speed"], 1)
		p = np.poly1d(z)
		plt.plot(sorted(speed_data["x_value"]), p(sorted(speed_data["x_value"])), linestyle="--", color="darkgray", label=f"Trend Line (y = {z[0]:.1f}x + {z[1]:.1f})")
	
	plt.title("Relationship Between X-class Flare Intensity and CME Speed", fontsize=14)
	plt.xlabel("X-class Value", fontsize=12)
	plt.ylabel("CME Speed (km/s)", fontsize=12)
	plt.grid(True, alpha=0.3)
	plt.legend()
	plt.savefig("flare_speed_relationship.png", dpi=300, bbox_inches="tight")
	
	print("\nAnalysis completed and visualizations created!")
	return {
		"halo_cme_comparison": {
			"all_flares_pct": all_halo_pct,
			"top50_pct": top50_halo_pct
		},
		"temporal_distribution": monthly_data,
		"speed_relationship": speed_data
	}


analysis_results = analyze_flares(nasa_df_with_matches)

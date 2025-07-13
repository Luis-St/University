# Data Science - Task 2
# Luis Staudt & Ismael David Quinonero Angel

import sqlite3
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from matplotlib.ticker import FuncFormatter

# Set plotting styles
plt.style.use("seaborn-v0_8-whitegrid")
sns.set_palette("deep")
plt.rcParams["figure.figsize"] = [12, 8]

# Connect to the SQLite database
sqlite_file = "./lahman_1871-2022.sqlite"
conn = sqlite3.connect(sqlite_file)


def example():
	salary_query = "SELECT yearID, sum(salary) as total_payroll FROM Salaries WHERE lgID == 'AL' GROUP BY yearID"
	team_salaries = pd.read_sql(salary_query, conn)
	return team_salaries.head()


print("Example of the Salaries table:")
print(example())
print()


def get_team_data():
	query = """
            SELECT Salaries.yearID,
                   Salaries.teamID,
                   SUM(Salaries.salary)        AS total_salary,
                   Teams.W                     AS wins,
                   Teams.G                     AS games,
                   (Teams.W * 100.0 / Teams.G) AS win_percentage,
                   Teams.franchID
            FROM Salaries
                     JOIN Teams
                          ON Salaries.yearID = Teams.yearID AND Salaries.teamID = Teams.teamID
            GROUP BY Salaries.yearID,
                     Salaries.teamID
            ORDER BY Salaries.yearID,
                     Salaries.teamID;
			"""
	
	team_year_salaries = pd.read_sql_query(query, conn)
	return team_year_salaries

print("Team salaries and wins")
df = get_team_data()
print(df.isnull().sum())
print()
print(f"Teams with $0 salary data: {(df['total_salary'] == 0).sum()}")
print(df.head(n=50))
print()

filtered_df = df[(df["yearID"] >= 1990) & (df["yearID"] <= 2014) & (df["total_salary"] > 0)].copy()
# Calculate standardized salary
yearly_means = filtered_df.groupby("yearID")["total_salary"].mean()
yearly_stds = filtered_df.groupby("yearID")["total_salary"].std()

# Add standardized salary to the dataframe
filtered_df["mean_salary_by_year"] = filtered_df["yearID"].map(yearly_means)
filtered_df["std_salary_by_year"] = filtered_df["yearID"].map(yearly_stds)
filtered_df["standardized_salary"] = (filtered_df["total_salary"] - filtered_df["mean_salary_by_year"]) / filtered_df["std_salary_by_year"]

# Display summary of standardized salaries
print(filtered_df[["yearID", "teamID", "total_salary", "standardized_salary"]].head(50))


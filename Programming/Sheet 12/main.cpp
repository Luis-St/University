#include <iostream>
#include <string>
#include <fstream>
#include <algorithm>
#include <cstring>
#include <ctime>

using namespace std;

struct TableEntry;
string getGoalDifference(TableEntry entry);
int getPoints(TableEntry entry);
void printEntry(TableEntry entry);
void readEntries(TableEntry *entries);
void writeEntries(TableEntry *entries);
int contains1(string s, string m);
int contains2(const char *s, const char *m);
void runtimeMeasurementChar(string str1, string str2);
void runtimeMeasurementString(string str1, string str2);
void task1();
void task2();
void task3();

struct TableEntry {
	short place;
	char club[30];
	short wonGames;
	short drawGames;
	short lostGames;
	short goals;
	short goalsAgainst;
};

string getGoalDifference(TableEntry entry) {
	int difference = entry.goals - entry.goalsAgainst;
	string str = "";
	if (difference == 0) {
		return "0";
	} else if (difference < 0) {
		str += to_string(difference);
	} else {
		str += "+" + to_string(difference);
	}
	return str.length() == 2 ? " " + str : str;
}

int getPoints(TableEntry entry) {
	return (3 * entry.wonGames + entry.drawGames);
}

void printEntry(TableEntry entry) {
	printf("%2.d %.30s\t%2d %2d %2d %2d:%2d %s %2d\n", entry.place, entry.club, entry.wonGames, entry.drawGames, entry.lostGames, entry.goals, entry.goalsAgainst, getGoalDifference(entry).c_str(), getPoints(entry));
}

void readEntries(TableEntry *entries) {
	//ifstream in(R"(D:\Programmieren\Git Repositories\Cpp-Sheets\Sheet 12\table_in.txt)");
	ifstream in(R"(C:\Users\adm-staudt\Git Repositories\Cpp-Sheets\Sheet 12\table_in.txt)");
	if (!in) {
		cerr << "Fail open file" << endl;
	} else {
		for (int i = 0; i < 18; ++i) {
			string goals;
			in >> entries[i].place >> entries[i].club >> entries[i].wonGames >> entries[i].drawGames >> entries[i].lostGames >> goals;
			int j = goals.find(':');
			entries[i].goals = (short) atoi(goals.substr(0, j).c_str());
			entries[i].goalsAgainst = (short) atoi(goals.substr(j + 1, goals.length()).c_str());
		}
	}
	in.close();
}

void writeEntries(TableEntry *entries) {
	//FILE *file = fopen(R"(D:\Programmieren\Git Repositories\Cpp-Sheets\Sheet 12\table_out.txt)", "w");
	FILE *file = fopen(R"(C:\Users\adm-staudt\Git Repositories\Cpp-Sheets\Sheet 12\table_out.txt)", "w");
	for (int i = 0; i < 18; ++i) {
		TableEntry entry = entries[i];
		fprintf(file, "%2.d %.30s\t\t\t%2d %2d %2d %2d:%2d %s %2d\n", entry.place, entry.club, entry.wonGames, entry.drawGames, entry.lostGames, entry.goals, entry.goalsAgainst, getGoalDifference(entry).c_str(), getPoints(entry));
	}
	fclose(file);
}

void task1() {
	TableEntry entries[18];
	readEntries(entries);
	/*for (const TableEntry &entry : entries) {
		printEntry(entry);
	}*/
	writeEntries(entries);
}

void task2() {
	//ifstream in(R"(D:\Programmieren\Git Repositories\Cpp-Sheets\Sheet 12\small.txt)");
	//ofstream out(R"(D:\Programmieren\Git Repositories\Cpp-Sheets\Sheet 12\large.txt)");
	ifstream in(R"(C:\Users\adm-staudt\Git Repositories\Cpp-Sheets\Sheet 12\small.txt)");
	ofstream out(R"(C:\Users\adm-staudt\Git Repositories\Cpp-Sheets\Sheet 12\large.txt)");
	for (string str; getline(in, str);) {
		transform(str.begin(), str.end(), str.begin(), ::toupper);
		out << str << endl;
	}
	in.close();
	out.close();
}

int contains1(string s, string m) {
	int containsCount = 0;
	for (int i = 0; i < s.length(); ++i) {
		bool flag = false;
		for (int j = 0; j < m.length(); ++j) {
			int checkIndex = i + j;
			if (checkIndex < s.length() && s[checkIndex] == m[j]) {
				flag = true;
				continue;
			} else {
				flag = false;
				break;
			}
		}
		if (flag) {
			++containsCount;
		}
	}
	return containsCount;
}

int contains2(const char *s, const char *m) {
	int containsCount = 0;
	for (int i = 0; i < strlen(s); ++i) {
		bool flag = false;
		for (int j = 0; j < strlen(m); ++j) {
			int checkIndex = i + j;
			if (checkIndex < strlen(s) && s[checkIndex] == m[j]) {
				flag = true;
				continue;
			} else {
				flag = false;
				break;
			}
		}
		if (flag) {
			++containsCount;
		}
	}
	return containsCount;
}

// Make sure runtime measurement is correct

void runtimeMeasurementChar(string str1, string str2) {
	double start = clock();
	int charContains = contains1(str1, str2);
	cout << "String is " << charContains << " times present (" << clock() - start << "ms)" << endl;
}

void runtimeMeasurementString(string str1, string str2) {
	double start = clock();
	int stringContains = contains2(str1.c_str(), str2.c_str());
	cout << "String is " << stringContains << " times present (" << clock() - start << "ms)" << endl;
}

void task3() {
	string str1, str2;
	cout << "Enter string to be checked: ";
	getline(cin, str1);
	cout << "Enter check string: ";
	getline(cin, str2);
	runtimeMeasurementChar(str1, str2);
	runtimeMeasurementString(str1, str2);
}

int main(int argc, char *argv[]) {
	task1();
}

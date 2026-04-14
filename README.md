# Expense Tracker App

## Overview
Expense Tracker is an Android application developed using Kotlin and Jetpack Compose with Firebase backend integration.  
The app allows users to manage daily expenses, track income, and monitor financial activities in real time.

## Features

### Authentication
- User login and signup using Firebase Authentication
- Secure user session management

### Dashboard
- Displays total balance
- Shows total expense
- Displays recent transactions

### Expense Management (CRUD)
- Add Expense
- View Expense list
- Delete Expense
- Real-time data update from Firebase Firestore

### Income Management
- Add income
- View updated balance

### Validation
- Input validation for amount, date, category
- Error handling for empty fields

### UI
- Built using Jetpack Compose
- Responsive and clean UI
- Loading indicators

## Technology Stack

### Mobile App
- Kotlin
- Jetpack Compose
- MVVM Architecture
- Hilt Dependency Injection

### Backend
- Firebase Authentication
- Firebase Firestore Database

---

## Database Structure

### Collection: Expance
| Field | Type |
|------|------|
| id | String |
| uid | String |
|amount| String |
| date | String |
| type | String |
| note | String |

### Collection: TotalExpance
| Field | Type |
|------|------|
| uid | String |
| totalEx | Number |

### Collection: income
| Field | Type |
|--------|----------|
| income | Number |

---

## Installation

1. Clone repository
2. Open project in Android Studio
3. Sync Gradle
4. Run on emulator or device

---

## APK Download
Google Drive Link:
[PASTE_LINK_HERE](https://drive.google.com/file/d/1Z15IIVvI9i7HUbyhTdjOdnfVeqvD3rmb/view?usp=sharing)


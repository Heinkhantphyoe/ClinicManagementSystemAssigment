# Clinic Management System

A Java desktop application for managing core clinic operations such as users, patients, appointments, medical records, vitals, prescriptions, rosters, and payments.

## Features

- Role-based login flows for:
  - Admin
  - Doctor
  - Nurse
  - Receptionist
  - Patient
- Appointment booking and status updates
- Patient and medical record management
- Prescription and vital record handling
- Staff roster management
- Payment collection and receipt generation
- File-based data persistence using text files in `src/clinicmanagementsystem/data`

## Project Structure

- `src/clinicmanagementsystem/` – application source code
  - `gui/` – UI screens and role-specific dashboards
  - `model/` – domain models
  - `util/` – utility classes (file handling, validation, session helpers, etc.)
  - `data/` – text files used as the application's data store
- `build.xml` – Apache Ant build script
- `nbproject/` – NetBeans project configuration

## Requirements

- JDK 25 (project is configured with `javac.source=25` and `javac.target=25`)
- Apache Ant (or NetBeans with Ant support)

## Build and Run

From the repository root:

```bash
ant clean
ant run
```

You can also open the project in NetBeans and run it directly.

> Note: The app reads data from relative paths like `src/clinicmanagementsystem/data/...`, so run it from the project root.

## Default Login (Sample)

The seeded user data includes an admin account:

- Username: `admin`
- Password: `admin`

Additional sample users are available in `src/clinicmanagementsystem/data/users.txt`.


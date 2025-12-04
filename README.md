# Angadi_Bhavana_COMP_699_C
🎟 Happy Tails – Pet Care Management System
A web-based platform that connects pet owners, sitters, sellers, and community helpers in one system.

# Happy Tails 🐾

An all-in-one platform for pet owners, sitters, sellers, and community helpers: social feed, sitter bookings, shopping, and lost-and-found — built with Python (Flask) + HTML/CSS/JS. Scope and features align to the System Proposal (actors, use cases, and NFRs). :contentReference[oaicite:1]{index=1}

## Features (MVP)
- Pet profiles, social posts, and basic feed
- Pet sitting: availability, bookings, payments
- Commerce: browse catalog, orders/checkout
- Lost & Found: reports, sightings, rewards
- Email notifications (SMTP), OAuth2 sign-in

- Application-layer package diagram: 

<img width="1199" height="778" alt="image" src="https://github.com/user-attachments/assets/9f0617b1-52d4-4e2a-a398-becf7b48b44d" />

Prerequisites
Install the following tools on Windows before running the project:
•	Python 3.10 or above (from python.org)
•	pip (included with Python)
•	MySQL Server 8.0
•	MySQL Workbench 8.0
•	Visual Studio Code
•	Git (optional)
Verify Python installation:
python --version

Project Setup : 
1.	Download or extract the HappyTails project folder to your system.
2.	Open Visual Studio Code -> click File -> Open Folder-> select the HappyTails folder.
3.	Open a new terminal inside VS Code: Terminal -> New Terminal
4.	Create a virtual environment: python -m venv venv
5.	Activate the virtual environment: venv\Scripts\activate
Install Required Libraries
  In the activated environment, run:
        pip install -r requirements.txt
  This installs Flask, SQLAlchemy, and all dependencies.
Database Setup (MySQL)
1.	Open MySQL Workbench.
2.	Connect to Local instance MySQL80.
3.	Create the database:
4.	CREATE DATABASE happytails;
5.	Open your project's .env file and update:
DATABASE_URL=mysql+pymysql://root:YOUR_PASSWORD@localhost:3306/happytails
Replace YOUR_PASSWORD with your actual MySQL password.
6.	Initialize database tables:  python init_db.py
Run the Application
- Start the application using: python app.py
- After the server starts, open your browser and go to: http://127.0.0.1:5000
- You should now see the HappyTails homepage.
Stop the Application
- Press CTRL + C in the terminal to stop the server.




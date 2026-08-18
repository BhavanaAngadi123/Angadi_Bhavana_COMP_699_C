import os
import secrets

from dotenv import load_dotenv

load_dotenv()

# Use SECRET_KEY from the environment outside local development. A random
# per-process fallback avoids shipping a predictable secret in the repository.
SECRET_KEY = os.getenv("SECRET_KEY") or secrets.token_hex(32)

# Database
SQLALCHEMY_DATABASE_URI = os.getenv(
    "DATABASE_URL",
    "sqlite:///instance/happy.db"
)
SQLALCHEMY_TRACK_MODIFICATIONS = False

# Upload folder
UPLOAD_FOLDER = os.path.join(os.getcwd(), "static", "uploads")

# Mail settings
MAIL_SERVER = os.getenv("MAIL_SERVER", "smtp.gmail.com")
MAIL_PORT = int(os.getenv("MAIL_PORT", 587))
MAIL_USE_TLS = os.getenv("MAIL_USE_TLS", "True").lower() == "true"
MAIL_USE_SSL = os.getenv("MAIL_USE_SSL", "False").lower() == "true"
MAIL_USERNAME = os.getenv("MAIL_USERNAME")
MAIL_PASSWORD = os.getenv("MAIL_PASSWORD")
MAIL_DEFAULT_SENDER = os.getenv("MAIL_DEFAULT_SENDER", MAIL_USERNAME)

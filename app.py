import os

from flask import Flask
from flask_mail import Mail
from flask_migrate import Migrate

from utils.db import db

# Blueprints
from routes.auth import auth_bp
from routes.owner import owner_bp
from routes.sitter import sitter_bp
from routes.seller import seller_bp
from routes.community import community_bp
from routes.admin import admin_bp
from routes.main import main_bp


def create_app():
    app = Flask(__name__)
    app.config.from_object("config")

    # Initialize extensions
    db.init_app(app)
    Migrate(app, db)
    Mail(app)

    # Register blueprints
    app.register_blueprint(auth_bp)
    app.register_blueprint(owner_bp)
    app.register_blueprint(sitter_bp)
    app.register_blueprint(seller_bp)
    app.register_blueprint(community_bp)
    app.register_blueprint(admin_bp)
    app.register_blueprint(main_bp)

    return app


app = create_app()

if __name__ == "__main__":
    # Debug mode is opt-in for local development and is disabled by default.
    debug_mode = os.getenv("FLASK_DEBUG", "false").lower() in {"1", "true", "yes"}
    app.run(debug=debug_mode)

/*
Administrator - responsible for managing user accounts, managing photo content, and managing permissions and access levels.
Guest - a user that has limited access to the photos, like view only.
User - a user that has access to all photos, like view, download, and comment.
Premium user - a user that has access to exclusive content and features, like high-resolution downloads.
Uploader - responsible for uploading photos, editing their metadata, and managing their visibility and privacy settings.
Moderator - responsible for reviewing and approving photos, managing comments and feedback, and enforcing community guidelines.
Analytics - responsible for tracking and analyzing user engagement, photo views, and other metrics.
Marketing - responsible for promoting and advertising the photos project.
Sales - responsible for selling photos, merchandise, and other products.
Customer service - responsible for addressing user inquiries and concerns.
This is not an exhaustive list and depending on the requirements of the project other roles may be added or combined.
 */

INSERT INTO roles (id, name, description) VALUES (1, 'ADMIN'," responsible for managing user accounts, managing photo content, and managing permissions and access levels" );
INSERT INTO roles (id, name, description) VALUES (2, 'GUEST'," a user that has limited access to the photos, like view only" );
INSERT INTO roles (id, name, description) VALUES (3, 'USER'," a user that has access to all photos, like view, download, and comment" );
INSERT INTO roles (id, name, description) VALUES (4, 'PREMIUM_USER'," a user that has access to exclusive content and features, like high-resolution downloads" );
INSERT INTO roles (id, name, description) VALUES (5, 'UPLOADER'," responsible for uploading photos, editing their metadata, and managing their visibility and privacy settings" );
INSERT INTO roles (id, name, description) VALUES (6, 'MODERATOR'," responsible for reviewing and approving photos, managing comments and feedback, and enforcing community guidelines" );
INSERT INTO roles (id, name, description) VALUES (7, 'ANALYTICS'," responsible for tracking and analyzing user engagement, photo views, and other metrics" );
INSERT INTO roles (id, name, description) VALUES (8, 'MARKETING'," responsible for promoting and advertising the photos project" );
INSERT INTO roles (id, name, description) VALUES (9, 'SALES'," responsible for selling photos, merchandise, and other products" );
INSERT INTO roles (id, name, description) VALUES (10, 'CUSTOMER_SERVICE'," responsible for addressing user inquiries and concerns" );


{
    "name": "ADMINISTRATOR",
    "description": "responsible for managing user accounts, managing photo content, and managing permissions and access levels"
}

{
    "name": "GUEST",
    "description": "a user that has limited access to the photos, like view only"
}

{
    "name": "USER",
    "description": "a user that has access to all photos, like view, download, and comment"
}

{
    "name": "PREMIUM_USER",
    "description": "a user that has access to exclusive content and features, like high-resolution downloads"
}

{
    "name": "UPLOADER",
    "description": "responsible for uploading photos, editing their metadata, and managing their visibility and privacy settings"
}

{
    "name": "MODERATOR",
    "description": "responsible for reviewing and approving photos, managing comments and feedback, and enforcing community guidelines"
}

{
    "name": "ANALYTICS",
    "description": "responsible for tracking and analyzing user engagement, photo views, and other metrics"
}

{
    "name": "MARKETING",
    "description": "responsible for promoting and advertising the photos project"
}

{
    "name": "SALES",
    "description": "responsible for selling photos, merchandise, and other products"
}

{
    "name": "CUSTOMER_SERVICE",
    "description": "responsible for addressing user inquiries and concerns"
}
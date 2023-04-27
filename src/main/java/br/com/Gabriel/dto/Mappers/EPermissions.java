
package br.com.Gabriel.dto.Mappers;

public enum EPermissions {
    CREATE_ALBUMS {
        @Override
        public String toString() {
            return "Create albums";
        }
    },
    EDIT_ALBUMS {
        @Override
        public String toString() {
            return "Edit albums";
        }
    },
    DELETE_ALBUMS {
        @Override
        public String toString() {
            return "Delete albums";
        }
    },
    VIEW_ALBUMS {
        @Override
        public String toString() {
            return "View albums";
        }
    },
    UPLOAD_PHOTOS {
        @Override
        public String toString() {
            return "Upload photos";
        }
    },
    EDIT_PHOTOS {
        @Override
        public String toString() {
            return "Edit photos";
        }
    },
    DELETE_PHOTOS {
        @Override
        public String toString() {
            return "Delete photos";
        }
    },
    VIEW_PHOTOS {
        @Override
        public String toString() {
            return "View photos";
        }
    },
    CREATE_COMMENTS {
        @Override
        public String toString() {
            return "Create comments";
        }
    },
    EDIT_COMMENTS {
        @Override
        public String toString() {
            return "Edit comments";
        }
    },
    DELETE_COMMENTS {
        @Override
        public String toString() {
            return "Delete comments";
        }
    },
    VIEW_COMMENTS {
        @Override
        public String toString() {
            return "View comments";
        }
    },
    LIKE_PHOTOS {
        @Override
        public String toString() {
            return "Like photos";
        }
    },
    DESLIKE_PHOTOS {
        @Override
        public String toString() {
            return "Deslike photos";
        }
    },
    VIEW_LIKES {
        @Override
        public String toString() {
            return "View likes";
        }
    },
    SHARE_PHOTOS {
        @Override
        public String toString() {
            return "Share photos";
        }
    },
    DOWNLOAD_PHOTOS {
        @Override
        public String toString() {
            return "Download photos";
        }
    },
    VIEW_USER_PROFILES {
        @Override
        public String toString() {
            return "View user profiles";
        }
    },
    EDIT_USER_PROFILES {
        @Override
        public String toString() {
            return "Edit user profiles";
        }
    },
    CREATE_USER_ACCOUNTS {
        @Override
        public String toString() {
            return "Create user accounts";
        }
    },
    DELETE_USER_ACCOUNTS {
        @Override
        public String toString() {
            return "Delete user accounts";
        }
    },
    BLOCK_USER_ACCOUNTS {
        @Override
        public String toString() {
            return "Block user accounts";
        }
    },
    ASSIGN_ROLES_TO_USERS {
        @Override
        public String toString() {
            return "Assign roles to users";
        }
    },
    REVOKE_ROLES_FROM_USERS {
        @Override
        public String toString() {
            return "Revoke roles from users";
        }
    },
    VIEW_ACTIVITY_LOGS {
        @Override
        public String toString() {
            return "View activity logs";
        }
    },
    EXPORT_DATA {
        @Override
        public String toString() {
            return "Export data";
        }
    },
    IMPORT_DATA {
        @Override
        public String toString() {
            return "Import data";
        }
    },
    SEARCH_FOR_CONTENT {
        @Override
        public String toString() {
            return "Search for content";
        }
    },
    CREATE_TAGS {
        @Override
        public String toString() {
            return "Create tags";
        }
    },
    EDIT_TAGS {
        @Override
        public String toString() {
            return "Edit tags";
        }
    },
    DELETE_TAGS {
        @Override
        public String toString() {
            return "Delete tags";
        }
    },
    SORT_PHOTOS_BY_DATE_NAME_OR_TAGS {
        @Override
        public String toString() {
            return "Sort photos by date, name or tags";
        }
    },
    CREATE_COLLECTIONS {
        @Override
        public String toString() {
            return "Create collections";
        }
    },
    EDIT_COLLECTIONS {
        @Override
        public String toString() {
            return "Edit collections";
        }
    },
    DELETE_COLLECTIONS {
        @Override
        public String toString() {
            return "Delete collections";
        }
    },
    ADD_PHOTOS_TO_COLLECTIONS {
        @Override
        public String toString() {
            return "Add photos to collections";
        }
    },
    REMOVE_PHOTOS_FROM_COLLECTIONS {
        @Override
        public String toString() {
            return "Remove photos from collections";
        }
    },
    VIEW_COLLECTIONS {
        @Override
        public String toString() {
            return "View collections";
        }
    },
    DOWNLOAD_COLLECTIONS {
        @Override
        public String toString() {
            return "Download collections";
        }
    }
}

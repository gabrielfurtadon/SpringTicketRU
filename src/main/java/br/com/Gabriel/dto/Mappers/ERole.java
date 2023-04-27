package br.com.Gabriel.dto.Mappers;

public enum ERole {
    STUDENT {
        @Override
        public String toString() {
            return "STUDENT";
        }
    },
    GUEST {
        @Override
        public String toString() {
            return "GUEST";
        }
    },
    USER {
        @Override
        public String toString() {
            return "USER";
        }
    },
    ADMIN {
        @Override
        public String toString() {
            return "ADMIN";
        }
    },

}
/*
 * Administrator - responsible for managing user accounts, managing photo
 * content, and managing permissions and access levels.
 * Moderator - responsible for reviewing and approving photos, managing comments
 * and feedback, and enforcing community guidelines.
 * Uploader - responsible for uploading photos, editing their metadata, and
 * managing their visibility and privacy settings.
 * Viewer - responsible for browsing and searching for photos, viewing and
 * downloading photos, and leaving comments and feedback.
 * Guest - a user that has limited access to the photos, like view only.
 * Premium user - a user that has access to exclusive content and features, like
 * high-resolution downloads.
 * Analytics - responsible for tracking and analyzing user engagement, photo
 * views, and other metrics.
 * Marketing - responsible for promoting and advertising the photos project.
 * Sales - responsible for selling photos, merchandise, and other products.
 * Customer service - responsible for addressing user inquiries and concerns.
 * This is not an exhaustive list and depending on the requirements of the
 * project other roles may be added or combined.
 * Banned - a user that has been banned from the photos project.
 */
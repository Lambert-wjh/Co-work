/*
 * This file is part of the enum type data structure in the HeadhunterMS project.
 * It includes five types of status for Project which is signed by company.
 *
 * @IN_PROGRESS     : The project is in progress.
 * @PAUSED          : The project is on hold.
 * @COMPLETED       : The project was completed in this month.
 * @ARCHIVED        : The project was completed before this month and has been archived.
 * @REVOKED         : The project has been cancelled.
 */
package entity.enums;

public enum Status {
    IN_PROGRESS, PAUSED, COMPLETED, ARCHIVED, REVOKED
}

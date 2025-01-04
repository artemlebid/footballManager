package org.artemlebid.footballmanager.constants;

public interface Messages {
    String PLAYER_SAVED = "Player saved successfully with id: %d";
    String PLAYER_UPDATED = "Player with id: %d successfully updated";
    String PLAYER_DELETED = "Player with id: %d successfully deleted";

    String TEAM_SAVED = "Team created successfully with id: %d";
    String TEAM_UPDATED = "Team with id: %d successfully updated";
    String TEAM_DELETED = "Team with id: %d successfully deleted";

    String NEW_PLAYERS_SAVED = "New players added successfully to team with id: %d";
    String NEW_PLAYERS_UPDATED = "Players updated successfully in team with id: %d";

    String TRANSFER_SUCCESS = "Transfer successful";

    String TEAM_NOT_FOUND = "Team not found with id: %d";
    String PLAYER_NOT_FOUND = "Player not found with id: %d";
    String PLAYER_ALREADY_EXIST = "Player with id: %d already have a different team";
    String NOT_ALLOWED_TRANSFER_1 = "Transfer not allowed because player don't have any team";
    String NOT_ALLOWED_TRANSFER_2 = "Transfer not allowed " +
                                    "because player cannot transfer from a team he is not in";
    String NOT_ALLOWED_TRANSFER_3 = "Transfer not allowed because " +
                                    "insufficient funds in the receiving team. Transfer cost is %f";

    String PLAYER_FIRST_NAME_NOT_EMPTY = "Player first name cannot be empty";
    String PLAYER_LAST_NAME_NOT_EMPTY = "Player last name cannot be empty";
    String PLAYER_POSITION_NOT_EMPTY = "Player position cannot be empty";
    String PLAYER_AGE_NOT_NULL = "Player age cannot be empty";
    String PLAYER_MIN_AGE = "Player age should be greater than 0";
    String PLAYER_EXPERIENCE_NOT_NULL = "Player experience cannot be empty";
    String PLAYER_MIN_EXPERIENCE = "Player experience should be greater or equal 0";

    String TEAM_NAME_NOT_EMPTY = "Team name cannot be empty";
    String TEAM_COUNTRY_NOT_EMPTY = "Team country cannot be empty";
    String TEAM_BALANCES_NOT_EMPTY = "Team balances cannot be empty";
    String TEAM_MIN_BALANCE = "Team minimum balance should be greater or equal 0";
    String TEAM_COMMISSIONS_NOT_EMPTY = "Team commissions cannot be empty";
    String TEAM_COMMISSION_VALUE = "Team minimum commission should be between 0 and 10";

    String PLAYER_IDENTIFIERS_CANT_BE_NULL = "Player identifiers cannot be null";

    String TRANSFER_PLAYER_ID_CANT_BE_NULL = "Transfer player id cannot be null";
    String TRANSFER_FROM_TEAM_ID_CANT_BE_NULL = "Transfer from team id cannot be null";
    String TRANSFER_TO_TEAM_ID_CANT_BE_NULL = "Transfer to team id cannot be null";
}

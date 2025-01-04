# Документація

### Для гравця

* GET /api/players - отримати список всіх гравців із БД

* POST /api/players - додати нового гравця. На вхід передати обʼєкт "CreatePlayerDto"

* GET /api/players/{playerId} - отримати одного гравця за його id

* PATCH /api/players/{playerId} - оновити гравця за його id. Передати "UpdatePlayerDto"

* DELETE /api/players/{playerId} - видалити гравця по його id.

### Для команди

* GET /api/teams - отримати список всіх команд із БД

* POST /api/teams - додати нову команду. На вхід передати обʼєкт "CreateTeamDto"

* GET /api/teams/{teamID} - отримати команду за її id

* PATCH /api/teams/{teamID} - оновити команду. На вхід передати обʼєкт "UpdateTeamDto"

* DELETE /api/teams/{teamID} - видалити команду

* GET /api/teams/{teamID}/players - отримати список всіх гравців команди за її id

* POST /api/teams/{teamID}/players - додати гравців до команди за її id. Передати на вхід обʼєкт "AddPlayerToTeamDto"

* PATCH /api/teams/{teamID}/players - оновити гравців в команді за її id. Передати на вхід обʼєкт "UpdatePlayerInTeamDto"

* POST /api/transfers - виконати трансфер між командами. На вхід передати обʼєкт "TransferRequestDto"








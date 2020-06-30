# Battle City - client side

## Version 0.1.0

- Project Initialization

## Version 0.2.0

- Implemented:
  - Movement of the main tank using the arrows or _w, d, s, a_ keys.
  - _Rotation_ of the main tank according to key that was pressed.
  - `Start` button to initialize the game. 
  - Client connection with the server.
- Pending:
  - Send tank position to the client.
  - Receive server approval to perform the movement.
  
## Version 0.3.0

- Implemented:
  - The client sends to the server the parameters _x, y and orientation_ to perform the movement.
  - The client now receives the parameters _x, y and orientation_ from the server, in order to replicate the movement from the order clients.
  - Many clients replicate the movement of the first client.
- Pending:
  - Lobby screen, in order to wait for a minimum number of players.
  - Each client needs its own id.
  - Each client needs its own tank.
  - Collisions between tanks. 

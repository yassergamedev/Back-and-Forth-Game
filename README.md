# The Back and Forth Game

## Overview

"The Back and Forth Game" is a casual multiplayer game that leverages multithreading for its execution. The game involves two teams, each comprised of four players, with each team controlled by a different person. The primary objective is to pass a ball among players of the same team, starting from the player nearest to the hoop and progressing towards the farthest player. Subsequently, the ball is passed from the farthest player back to the first player, with each player responsible for passing the ball to the next teammate. Points are earned when a team successfully completes the back and forth passing sequence, and the first team to accumulate 10 points emerges as the victor. The unique challenge lies in coordinating the actions of all players, each executing in distinct threads simultaneously.

## How It Works

In the context of the Back and Forth Game, the gameplay centers around four players and a single ball. Consequently, only one player can hold the ball at any given time and initiate its transfer to the next player on their team. However, due to the utilization of separate threads for each player's execution, proper concurrency management becomes crucial for seamless gameplay.

To ensure that a player possesses the ball only when it's their designated turn, a condition is established. This condition is met when a player successfully transfers the ball to the subsequent player. If the ball is inadvertently dropped during the transfer, the condition remains unsatisfied, and the player who failed to pass the ball retains possession. At the outset of a player's thread execution, they assess this condition. Upon fulfillment, the player is granted the ability to hold the ball, pass it to the next team member, and subsequently reevaluate the condition.

In situations where offline play is desired, a pause button assumes significance. The Back and Forth Game accommodates this requirement by implementing a pause functionality. The innovative approach involves grouping all player threads together. Consequently, when pausing the game, concerns about individual threads persisting are eliminated, making this approach superior to pausing each thread independently.

## Instructions

1. Clone this repository to your local machine.
2. Open Eclipse IDE.
3. Go to `File` > `Import`.
4. Choose `General` > `Existing Projects into Workspace` and click `Next`.
5. Select the root directory where you cloned the repository.
6. Eclipse should detect the project. Ensure it's checked and click `Finish`.
7. Locate the main project file that contains the game logic (e.g., `MainGame.java` or similar).
8. Right-click on the main project file and select `Run As` > `Java Application`.
9. The game should start running. Follow on-screen instructions to control your team and pass the ball strategically to earn points.
10. During offline play, you can utilize the pause button to freeze all threads simultaneously and pause the game's execution.

Feel free to contribute, suggest improvements, and provide feedback!

## License

This project is licensed under the [MIT License](LICENSE).


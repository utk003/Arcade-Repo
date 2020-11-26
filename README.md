# Arcade Repo
This is the EOY project for the APCSDS class we took in 10th grade.

Contributors: [Aditya S.](https://github.com/baboondude), [Eric Z.](https://github.com/stephsplash30), [Ray W.](https://github.com/rbuckets), [Utkarsh P.](https://github.com/utk003)

## What Is This?
This is a collection of 7 classic arcade games that we implemented in Java: [2048](https://en.wikipedia.org/wiki/2048_(video_game)), [Breakout](https://en.wikipedia.org/wiki/Breakout_(video_game)), [Flappy Bird](https://en.wikipedia.org/wiki/Flappy_Bird), [Minesweeper](https://en.wikipedia.org/wiki/Minesweeper_(video_game)), [Pac-Man](https://en.wikipedia.org/wiki/Pac-Man), [Snake](https://en.wikipedia.org/wiki/Snake_(video_game_genre)), and [Tetris](https://en.wikipedia.org/wiki/Tetris).

Any of these games can be played directly using the code available in their respective folders. An encompassing arcade UI was planned at the time of the project, but was ultimately abandoned due to issues with completing the project before the deadline. As it is, the finishing touches on many of the games were put in minutes before the project presentation. If this project is ever revived, this UI will be an important project goal. For more information, see Planned Features.

### 2048
Not much needs to be said for this game. It works exactly like the original.

An interesting factoid about our implementation of this game is that, for a brief moment in its development, the tile movement was so severely messed up that tiles would collide with one another as they moved across the board. Luckily the issue was quickly patched out.

Main Code Contributors: Aditya, Utkarsh

Main Graphics Contributors: Aditya

### Breakout
Once again, this game functions quite similarly to the original.

Beware a peculiar timing glitch where the ball can get stuck within the paddle.

Main Code Contributors: Ray

Main Graphics Contributors: Ray

### Flappy Bird
This game also functions quite like the original.

The only significant deviation is this version's imperfect gravity (but that's a feature, not a bug :wink:).

Main Code Contributors: Aditya

Main Graphics Contributors: Aditya

### Minesweeper
Yet again, this game functions just like the original. (*I'm beginning to sense a pattern here....*)

One interesting quirk about this implementation is that, at one point, it was possible to lose immediately if you had the misfortune of clicking on a square with a mine on the first move. I believe that bug was patched, but don't quote me on it.

Main Code Contributors: Aditya

Main Graphics Contributors: Aditya

### Pac-Man
Pac-Man is basically the only game from this collection which has significant differences from its original. The most significant is its level design (we created a custom level in honor of the class for which this project was created). However, the overall game mechanics, such as the ghosts' AIs and the power pellets, are still the same ones we know and love.

This game was also the most difficult to create, due in large part to the multi-threading we implemented to make the ghosts and Pac-Man run indepedently of the main rendering thread. For a group of 4 students who had just completed APCS, I must say I am quite impressed with the quality of the game (definitely no biases here :innocent:).

Main Code Contributors: Utkarsh

Main Graphics Contributors: Eric

### Snake
Snake is also very faithful to the original. But, in all honestly, it's kind of hard to mess that up.

This is the only other game (to my knowledge) where we tried out multi-threading. I suppose that was mainly because it was a new toy for me to mess around with.

Main Code Contributors: Utkarsh

Main Graphics Contributors: Utkarsh

### Tetris
Yet again, this implementation is quite faithful to the original. Slight deviations include the lack of SZ-protection (that is to say, the S and Z tetrads can appear indiscriminately, which is now [considered illegal](https://en.wikipedia.org/wiki/Tetris#Infinite_game_question) for any official Tetris implementation as of 2001).

Though the final game on our list, Tetris is still impressive. However, this is the only game we did not create explicityly for this project. Instead, this code comes from the Tetris Lab we worked on for this course earlier that year.

Main Code Contributors: Aditya

Main Graphics Contributors: Aditya

## "Planned" Features
These are some planned features. Well, as planned as features can be for a project which was discontinued and hasn't seen any work in ages.
### An Arcade UI
An arcade UI would provide a unified interface for starting games, displaying high scores, and many other interesting features. We initially had plans for this, but they were discarded due to issues with meeting the project deadline with the games themselves.

### Game Upgrades
#### 2048
* More tiles (the ones above 4096)

#### Breakout
* Some bug fixes to make gameplay smoother
* Color for the ball, bricks, and paddle

#### Flappy Bird
* Gravity fix
* Night/Dark Mode

#### Minesweeper
* *I don't even know.... maybe someone else can fill this in later....*

#### Pac-Man
* More levels
* Smoother control of Pac-Man

#### Snake
* Different sized boards

#### Tetris
* A more official rule set

## How Can I Play?
For now, you will have to run each game individually from within its own folder by creating an instance of the game. However, the planned arcade UI will hopefully unify the process and make it much simpler (see Planned Features).

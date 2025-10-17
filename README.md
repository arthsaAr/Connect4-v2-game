# 🎮 Connect4 v2 Game – Human vs AI (Java + Swing)

A 2D implementation of the classic **Connect 4** game in Java using **Swing** for the GUI.  
This version allows a **human player to compete against an AI** opponent. Players take turns dropping colored discs into a 7x6 grid. The first player to connect four discs vertically, horizontally, or diagonally wins the game.

---

## 🧠 Features
- Two-player mode: Human vs AI.
- Turn-based gameplay with real-time win detection.
- AI opponent uses a simple algorithm to decide moves.
- Graphical interface using **Java Swing**.
- Option to restart the game after a win or draw.
- Interactive and responsive user interface.

---

## 🛠️ Tech Stack
- **Language:** Java  
- **GUI Framework:** Swing  
- **IDE:** Any Java IDE (e.g., IntelliJ IDEA, Eclipse, NetBeans)  

---

## 📂 Project Structure
```
Connect4-v2-game/
│
├── src/
│ ├── ConnectFour.java
│ ├── GUIBoard.java
│ └── GUIHumanPlayer.java
│
├── lib/ # Precompiled .class files
│ ├── Board.class
│ ├── Move.class
│ ├── Player.class
│ ├── RandomPlayer.class
│ ├── gameButton.class
│ ├── GUIBoard.class
│ ├── GUIHumanPlayer.class
│ └── HumanPlayer.class
│
├── README.md
└── .gitignore
```
## ▶️ How to Compile & Run

All `.class` files in `lib/` are required to run the project

### On Linux / macOS
```bash
# Compile all java files
javac -cp lib/ *.java

# Run the game
java -cp lib/:. ConnectFour

### On Windows
# Compile
javac -cp lib\ *.java

# Run
java -cp lib\;. ConnectFour

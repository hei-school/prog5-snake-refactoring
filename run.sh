#!/bin/bash

echo "Compilation dans dossier temporaire..."
mkdir -p out

javac -d out snake/*.java
if [ $? -ne 0 ]; then
    echo "❌ Erreur de compilation."
    exit 1
fi

echo "Lancement du jeu..."
java -cp out snake.SnakeGame

echo "Nettoyage..."
rm -rf out

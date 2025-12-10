#!/bin/bash

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

echo -e "${YELLOW}Building Snake Game...${NC}"

mkdir -p bin

javac -d bin snake/*.java

if [ $? -eq 0 ]; then
    echo -e "${GREEN}Compilation successful!${NC}"
    echo -e "${YELLOW}Starting Snake Game...${NC}"
    echo -e "${YELLOW}Controls: w=up, s=down, a=left, d=right${NC}"
    echo ""

    old_tty_settings=$(stty -g)

    stty raw -echo

    java -cp bin snake.SnakeGame

    stty "$old_tty_settings"

    echo -e "\n${GREEN}Game ended. Terminal restored.${NC}"
else
    echo -e "${RED}Compilation failed!${NC}"
    exit 1
fi
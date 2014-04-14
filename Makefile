all:
	java -jar antlr.jar src/client/boardlang/Board.g4

watch:
	nodemon -w . -e .g4 --exec "make"

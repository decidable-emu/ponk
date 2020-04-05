#pragma once

class Game
{

public:
	static void Start();

private:
	static bool IsExiting();
	static void GameLoop();

	enum GameState { Uninitialised, ShowingSplash, Paused, ShowingMenu,
			Playing, Exiting };

	static GameState _gameState;
	static sf::RenderWindow _mainWindow;

};

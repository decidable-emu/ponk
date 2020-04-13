#include <SFML/Window.hpp>
#include <SFML/Graphics.hpp>
#include <iostream>
#include "inputDetector.cpp"
#include "simulator.cpp"
#include "renderer.cpp"


int main()
{

	sf::RenderWindow renderWindow(sf::VideoMode(1024, 720), "PONK");
	renderWindow.setKeyRepeatEnabled(false);

	sf::RectangleShape arena;
	sf::RectangleShape p1Paddle;
	sf::RectangleShape p2Paddle;
	sf::RectangleShape dot;
	sf::RectangleShape p1_points [19];
	sf::RectangleShape p2_points [19];

	sf::Texture menuImage;
	menuImage.loadFromFile("title_screen.png");
	sf::Sprite menu;
	menu.setTexture(menuImage);

	bool upKeyPressed    = false;
	bool downKeyPressed  = false;
	bool wKeyPressed     = false;
	bool sKeyPressed     = false;
	bool spaceKeyPressed = false;

	double dt = 0.016666f; //60 frames per second
	sf::Clock clock; //starts the clock

	double frame_start_time;
	double frame_end_time;

	int p1_score = 0;
	int p2_score = 0;

	createObjects(arena, p1Paddle,
			p2Paddle, dot,
			p1_points, p2_points);

	double 	p1_dp = 0,	//dp == speed
		p2_dp = 0;
	double  dot_dp_x = -.6f,
		dot_dp_y = 0;

	double  p1_p = 720/2,	//p  == position
		p2_p = 720/2;

	double  dot_p_x = (float)1024/2,
		dot_p_y = (float)720/2;

	enum GameState {MENU, PLAYING, FINISHED};

	GameState currentGameState = MENU;

	while(renderWindow.isOpen())
	{


		getUserInput(renderWindow, wKeyPressed, sKeyPressed, 
			upKeyPressed, downKeyPressed, spaceKeyPressed);

		if (currentGameState == PLAYING)
		{


		double p1_ddp = 0.f;	//ddp == acceleration
		double p2_ddp = 0.f;

		calculateVariables(p1_score, p2_score, 
				wKeyPressed, sKeyPressed, 
				upKeyPressed, downKeyPressed,
				p1_ddp, p2_ddp, 
				p1_dp, p2_dp, 
				p1_p, p2_p, 
				dot_p_x, dot_p_y, 
				dot_dp_x, dot_dp_y, 
				dt);

		drawShapes(renderWindow, arena, 
				p1Paddle, p2Paddle, 
				p1_points, p2_points, 
				p1_score, p2_score, 
				p1_p, p2_p, 
				dot, dot_p_x, dot_p_y);

		std::cout << p1_score << ' ' << p2_score << std::endl; 


		if (p1_score == 19)
		{
			menuImage.loadFromFile("player1wins.png");
			menu.setTexture(menuImage);
			currentGameState = FINISHED;
		}
		if (p2_score == 19)
		{
			menuImage.loadFromFile("player2wins.png");
			menu.setTexture(menuImage);
			currentGameState = FINISHED;
		}

		frame_end_time = clock.getElapsedTime().asMilliseconds();
		dt = frame_end_time - frame_start_time;


		//the end of the last frame becomes the start of the next
		frame_start_time = frame_end_time;

		}
		else if (currentGameState == MENU)
		{
			renderWindow.display();
			renderWindow.draw(menu);


/*			if (spaceKeyPressed)
			{
				p1_p = 720/2;
				p2_p = 720/2;
				//dot_dp_x = 0;
				dot_dp_y = 0;
				dot_p_x = 1024/2;
				dot_p_y = 720/2;
				p1_score = 0;
				p2_score = 0;
				dt = 0.016666f;
				currentGameState = PLAYING;
				clock.restart();
				dot_dp_x = -.6f;
*/


			if(spaceKeyPressed)
			{
			upKeyPressed    = false;
			downKeyPressed  = false;
			wKeyPressed     = false;
			sKeyPressed     = false;
			spaceKeyPressed = false;

			dt = 0.016666f; //60 frames per second
//			sf::Clock clock; //starts the clock
			clock.restart();
			frame_start_time;
			frame_end_time;

			p1_score = 0;
			p2_score = 0;

			createObjects(arena, p1Paddle,
					p2Paddle, dot,
					p1_points, p2_points);
			p1_dp = 0;
			p2_dp = 0;
			dot_dp_x = -.6f;
			dot_dp_y = 0;

			p1_p = 720/2;
			p2_p = 720/2;

			dot_p_x = (float)1024/2;
			dot_p_y = (float)720/2;
			currentGameState = PLAYING;
			}
		}
		else //if currentGameState == FINISHED
		{
			renderWindow.display();
			renderWindow.draw(menu);

			if (spaceKeyPressed)
			{
				renderWindow.close();
				main();
			}
		}
	}
	return 0;
}


#include <SFML/Window.hpp>
#include <SFML/Graphics.hpp>

int main()
{
	sf::RenderWindow renderWindow(sf::VideoMode(1024, 720), "PONK");
	renderWindow.setKeyRepeatEnabled(false);

	bool upKeyPressed = false;
	bool downKeyPressed = false;
	bool leftKeyPressed = false;
	bool rightKeyPressed = false;
	bool spaceKeyPressed = false;

	sf::RectangleShape p1Paddle(sf::Vector2f(25, 25));
	p1Paddle.setFillColor(sf::Color::Green);
	double p1PaddleX = 50;
	double p1PaddleY = 200;

	float delta_time = 0.016666f; //60 frames per second
	sf::Clock clock; //starts the clock
	double frame_start_time = clock.getElapsedTime().asMilliseconds();
	double frame_end_time;

	float speed = .5f;

	while(renderWindow.isOpen())
	{
		//getting user input
		sf::Event event;
		while (renderWindow.pollEvent(event))
		{
			if (event.type == sf::Event::Closed)
			{
	 			renderWindow.close();
			}
			else if (event.type == sf::Event::KeyPressed)
			{
				if (event.key.code == sf::Keyboard::Up)
				{
					upKeyPressed = true;
				}
				else if (event.key.code == sf::Keyboard::Down)
				{
					downKeyPressed = true;
				}
				else if (event.key.code == sf::Keyboard::Left)
				{
					leftKeyPressed = true;
				}
				else if (event.key.code == sf::Keyboard::Right)
				{
					rightKeyPressed = true;
				}
				else if (event.key.code == sf::Keyboard::Space)
				{
					spaceKeyPressed = true;
				}
			}
			else if (event.type == sf::Event::KeyReleased)
			{
				if (event.key.code == sf::Keyboard::Up)
				{
					upKeyPressed = false;
				}
				else if (event.key.code == sf::Keyboard::Down)
				{
					downKeyPressed = false;
				}
				else if (event.key.code == sf::Keyboard::Left)
				{
					leftKeyPressed = false;
				}
				else if (event.key.code == sf::Keyboard::Right)
				{
				 	rightKeyPressed = false;
				}
				else if(event.key.code == sf::Keyboard::Space)
				{
					spaceKeyPressed = false;
				}
			}
		}

		renderWindow.clear(sf::Color(255, 165, 0));
		//calculating what to render

		if (spaceKeyPressed)
		{
			speed = 1;
		}
		else
		{
			speed = .5f;
		}

		if (upKeyPressed)
		{
			p1PaddleY -= speed*delta_time;
		}
		if (downKeyPressed)
		{
			p1PaddleY += speed*delta_time;
		}
		if (leftKeyPressed)
		{
			p1PaddleX -= speed*delta_time;
		}
		if (rightKeyPressed)
		{
			p1PaddleX += speed*delta_time;
		}
		p1Paddle.setPosition(p1PaddleX, p1PaddleY);
		renderWindow.draw(p1Paddle);

		renderWindow.display();
		frame_end_time = clock.getElapsedTime().asMilliseconds();
		delta_time = frame_end_time - frame_start_time;

		//the end of the last frame becomes the start of the next
		frame_start_time = frame_end_time;

	}
	return 0;
}

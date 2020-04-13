

void getUserInput(sf::RenderWindow& window, bool& wKeyPressed,
				  bool& sKeyPressed, bool& upKeyPressed, bool& downKeyPressed,
				  bool& spaceKeyPressed)
{
	sf::Event event;
	while (window.pollEvent(event))
	{
		if (event.type == sf::Event::Closed)
		{
			window.close();
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
			else if (event.key.code == sf::Keyboard::W)
			{
				wKeyPressed = true;
			}
			else if (event.key.code == sf::Keyboard::S)
			{
				sKeyPressed = true;
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
			else if (event.key.code == sf::Keyboard::W)
			{
				wKeyPressed = false;
			}
			else if (event.key.code == sf::Keyboard::S)
			{
				sKeyPressed = false;
			}
			else if (event.key.code == sf::Keyboard::Space)
			{
				spaceKeyPressed = false;
			}

		}
	}
}


void createObjects(sf::RectangleShape& arena, sf::RectangleShape& p1Paddle, 
			sf::RectangleShape& p2Paddle, sf::RectangleShape& dot,
			sf::RectangleShape (&p1_points)[19], sf::RectangleShape (&p2_points)[19])
{

	arena.setSize(sf::Vector2f(1024-50, 720-150));
	arena.setPosition(50/2, 50/2+50);
	arena.setFillColor(sf::Color::Black);

	p1Paddle.setSize(sf::Vector2f(25, 150));
	p1Paddle.setOrigin(25.f/2, 150/2);
	p1Paddle.setFillColor(sf::Color::White);

	p2Paddle.setSize(sf::Vector2f(25, 150));
	p2Paddle.setOrigin(25.f/2, 150/2);
	p2Paddle.setFillColor(sf::Color::White);

	dot.setSize(sf::Vector2f(25, 25));
	dot.setOrigin(25.f/2, 25.f/2);

	for (int i=0; i<19; i++)
	{
		sf::RectangleShape shape(sf::Vector2f(25, 25));
		shape.setFillColor(sf::Color::Black);
		p1_points[i] = shape;
	}

	for (int i=0; i<19; i++)
	{
		sf::RectangleShape shape(sf::Vector2f(25, 25));
		shape.setFillColor(sf::Color::Black);
		p2_points[i] = shape;
	}


}


void drawShapes(sf::RenderWindow& window, 
		sf::RectangleShape& arena, 
		sf::RectangleShape& p1Paddle, sf::RectangleShape& p2Paddle,
		sf::RectangleShape (&p1_points)[19], sf::RectangleShape (&p2_points)[19], 
		int& p1_score, int& p2_score, 
		double& p1_p, double& p2_p, 
		sf::RectangleShape& dot, double& dot_p_x, double& dot_p_y)
{
	window.clear(sf::Color(0, 15, 128));
	window.draw(arena);

	p1Paddle.setPosition(50, p1_p);
	window.draw(p1Paddle);

	p2Paddle.setPosition(1024-50, p2_p);
	window.draw(p2Paddle);

	dot.setPosition(dot_p_x, dot_p_y);
	window.draw(dot);

	for (int i=0; i<19; i++)
	{
		sf::RectangleShape shape = p1_points[i];
		shape.setPosition(25 + i*25, 25);
		if (i<p1_score)
		{
			shape.setFillColor(sf::Color::White);
		}
		window.draw(shape);
	}

	for (int i=0; i<19; i++)
	{
		sf::RectangleShape shape = p2_points[i];
		//the shape is drawn from the top-left corner,
		//so we must subtract an extra 25 to account for the length
		//of the shape
		shape.setPosition(1024 - 25 - 25 - i*25, 25);
		if (i<p2_score)
		{
			shape.setFillColor(sf::Color::White);
		}
		window.draw(shape);
	}

	window.display();

}

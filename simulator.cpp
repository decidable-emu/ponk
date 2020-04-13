void calculateVariables(int& p1_score, int& p2_score, 
			bool& wKeyPressed, bool& sKeyPressed, 
			bool& upKeyPressed, bool& downKeyPressed, 
			double& p1_ddp, double& p2_ddp, 
			double& p1_dp, double& p2_dp, 
			double& p1_p, double& p2_p, 
			double& dot_p_x, double& dot_p_y, 
			double& dot_dp_x, double& dot_dp_y, 
			double& dt)
{

	//p1_p is a useful alias for calculating accelleration
	//whilst p1_paddle_y makes more sense for collision detection
	double& p1_paddle_y = p1_p;
	double& p2_paddle_y = p2_p;

	//the up/w keys increases the acceleration in the up direction
	if (wKeyPressed)
	{
		p1_ddp = -0.01f;
	}
	if (upKeyPressed)
	{
		p2_ddp = -0.01f;
	}
	//the down/s keys increases the acceleration in the down direction
	if (sKeyPressed)
	{
		p1_ddp = 0.01f;
	}
	if (downKeyPressed)
	{
		p2_ddp = 0.01f;
	}

	//friction reduces the acceleration relative to the speed
	p1_ddp -= p1_dp * 0.01;
	p2_ddp -= p2_dp * 0.01;

	//the speed and position for this frame are then calculated based on the acceleration
	//v = u + at
	//s += ut + 1/2 * at^2
	p1_dp = p1_dp + p1_ddp * dt;
	p1_p = p1_p + p1_dp * dt + .5f * p1_ddp * dt * dt;

	p2_dp = p2_dp + p2_ddp * dt;
	p2_p = p2_p + p2_dp * dt + .5f * p2_ddp * dt * dt;


	//calculation the position of the dot
	dot_p_x += dot_dp_x * dt;
	dot_p_y += dot_dp_y * dt;

	//collision detection
	double p1_paddle_x = 50;
	double p2_paddle_x = 1024-50;


	double  p1_halfsize_x	 = 25.f/2,
		p1_halfsize_y	 = 75,
		p2_halfsize_x	 = 25.f/2,
	        p2_halfsize_y    = 75;

	double  p1_topSide	= p1_paddle_y - p1_halfsize_y,
		p1_bottomSide	= p1_paddle_y + p1_halfsize_y,
		p1_leftSide	= p1_paddle_x - p1_halfsize_x,
		p1_rightSide	= p1_paddle_x + p1_halfsize_x;

	double  p2_topSide	= p2_paddle_y - p2_halfsize_y,
		p2_bottomSide	= p2_paddle_y + p2_halfsize_y,
		p2_leftSide	= p2_paddle_x - p2_halfsize_x,
		p2_rightSide	= p2_paddle_x + p2_halfsize_x;

	double  arena_topBorder = 75,
		arena_bottomBorder = 720-75,
		arena_leftBorder = 25,
		arena_rightBorder = 1024-25;

	double  dot_halfsize_x	= 25.f/2,
		dot_halfsize_y	= 25.f/2;

	double 	dot_topSide 	= dot_p_y - dot_halfsize_y,
		dot_bottomSide 	= dot_p_y + dot_halfsize_y,
		dot_leftSide	= dot_p_x - dot_halfsize_x,
		dot_rightSide	= dot_p_x + dot_halfsize_x;

	//paddles w/ walls
	if (p1_topSide < arena_topBorder)
	{
		p1_p = arena_topBorder + p1_halfsize_y;
		//p1_dp = -p1_dp;
	}
	if (p1_bottomSide > arena_bottomBorder)
	{
		p1_p = arena_bottomBorder - p1_halfsize_y;
		//p1_dp = -p1_dp;
	}

	if (p2_topSide < arena_topBorder)
	{
		p2_p = arena_topBorder + p2_halfsize_y;
	}
	if (p2_bottomSide > arena_bottomBorder)
	{
		p2_p = arena_bottomBorder - p2_halfsize_y;
	}

	//dot with right paddle
	if ((dot_bottomSide > p2_topSide) && //top edge
	(dot_leftSide < p2_rightSide) &&
	(dot_rightSide > p2_leftSide) &&
	(dot_topSide < p2_topSide))
	{
		//we set the position in addition to changing the speed
		//so that the dot doesn't get stuck in the walls at
		//high speeds
		dot_p_y = p2_topSide - dot_halfsize_y;
		dot_dp_y += p2_dp;
	}

	if ((dot_topSide < p2_bottomSide) && //bottom edge
	(dot_leftSide < p2_rightSide) &&
	(dot_rightSide > p2_leftSide) &&
	(dot_bottomSide > p2_bottomSide))
	{
		dot_p_y = p2_bottomSide + dot_halfsize_y;
		dot_dp_y += p2_dp;
	}

	if ((dot_rightSide > p2_leftSide) && //paddle left edge
	(dot_bottomSide > p2_topSide) &&
	 (dot_topSide < p2_bottomSide))
	{
		dot_p_x = p2_leftSide - dot_halfsize_x;
		dot_dp_x = -dot_dp_x;
		dot_dp_y += p2_dp * 0.25;
	}

	//dot with left paddle
	 if ((dot_bottomSide > p1_topSide) && //top edge
	(dot_leftSide < p1_rightSide) &&
	(dot_rightSide > p1_leftSide) &&
	(dot_topSide < p1_topSide))
	{
		dot_p_y = p1_topSide - dot_halfsize_y;
		dot_dp_y += p1_dp;
	}

	if ((dot_topSide < p1_bottomSide) && //bottom edge
	(dot_leftSide < p1_rightSide) &&
	(dot_rightSide > p1_leftSide) &&
	(dot_bottomSide > p1_bottomSide))
	{
		dot_p_y = p1_bottomSide + dot_halfsize_y;
		dot_dp_y += p1_dp;
	}

	if ((dot_leftSide < p1_rightSide) && //paddle right edge
	(dot_bottomSide > p1_topSide) &&
	 (dot_topSide < p1_bottomSide))
	{
		dot_p_x = p1_rightSide + dot_halfsize_x;
		dot_dp_x = -dot_dp_x;
		dot_dp_y += p1_dp * 0.25;
	}


	//dot with arena
	if (dot_topSide < arena_topBorder)
	{
		dot_p_y = arena_topBorder + dot_halfsize_y;
		dot_dp_y = -dot_dp_y;
	}
	if (dot_bottomSide > arena_bottomBorder)
	{
		dot_p_y = arena_bottomBorder - dot_halfsize_y;
		dot_dp_y = -dot_dp_y;
	}
	if (dot_leftSide < arena_leftBorder)
	{
		dot_dp_x = 0;
		dot_dp_y = 0;
		dot_p_x = 1024/2;
		dot_p_y = 720/2;
		p2_score++;
		std::cout << "p2 score = " << p2_score<< std::endl;
		dot_dp_x = -.6f;
	}
	if (dot_rightSide > arena_rightBorder)
	{
		dot_dp_x = 0;
		dot_dp_y = 0;
		dot_p_x = 1024/2;
		dot_p_y = 720/2;
		p1_score++;
		std::cout << "p1 score = " << p1_score << std::endl;
		dot_dp_x = .6f;
	}
}

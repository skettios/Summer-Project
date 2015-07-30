local player = EntityFactory:createPlayer(Assets:getTexture('player')):setInitialPosition(200, 100)
local counter = 0

local random = nil
local bgms = { Assets:getMusic('once_upon_a_me'), Assets:getMusic('shiawase_usagi'), Assets:getMusic('catch_your_dreams'), Assets:getMusic('yet_another_drizzly_rain') }
local background = GraphicFactory:createBackground(Assets:getTexture('level_1'))

function start(world)
  random = Random:new(System:currentTimeMillis())

--  bgms[random:nextInt(4) + 1]:play()

	world:setBackground(background)
	world:push(player)
end

function loop(world, deltaTime)
	if counter == 100 then
		local enemy = EntityFactory:createEnemy(Assets:getTexture('enemy')):setInitialPosition(200, 300)
		world:push(enemy)
	elseif counter == 200 then
		player:setColor(Color.RED)
	elseif counter == 300 then
		player:setColor(Color.BLUE)
	elseif counter == 400 then
	  player:setColor(Color.GREEN)	
  elseif counter == 500 then
	  LevelManager:setLevel('Level_1')  
	end

	counter = counter + deltaTime
end
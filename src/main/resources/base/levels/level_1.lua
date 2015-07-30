-- TODO(skettios): Get more assets >< ${version}

local player = EntityFactory:createPlayer(Assets:getTexture('enemy')):setInitialPosition(200, 100)
local background = GraphicFactory:createBackground(Assets:getTexture('level_1'))

local counter = 0

function start(world)
	world:setBackground(background)
	world:push(player)
end

function loop(world, deltaTime)
	counter = counter + 1
end
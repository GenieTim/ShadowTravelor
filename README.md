# ShadowTravelor

ShadowTravelor is an Android-App which aims to  reduce stress during rush hour as well as help prevent accidents on the streets.  

Your smartphone may be a super assistant to help you find the way or catch the bus at the right time 
BUT, it can also be a huge risk as it is easily disturbing, not only for yourself but for everyone 
else if you only listen to loud enough music. It's huge impact has to get under control!

We have set our goals in symbiosis with the Workshops by Zurich and the Swiss Transport Associations. 
We have found a solution following these criterias:

- autonomous
	-- Detects when you leave
	-- Detects what type of transport you are using
	-- Detects where you go
- Objective Assessment
	-- Music is distraction
	-- loud music is annoying
	-- Usage of phone while walking is not only for you dangerous
	-- Usage of public transport while rush hour is often unnecessary and also a risk
	
Our app **ShadowTravelor** uses gameification to motivate users to improve safety in traffic. 
The app tracks the user as soon as he leaves his home and only stops when he reaches his destiny. 
For every minute he spends walking without using his smartphone, he gets points. 
For every minute he bypasses rush hour, he gets points. 
The points system is really mature, as it is even dependent on the volume of music the user is listening to!

## Inspiration

We were mostly inspired by the workshop given by the Zurich/T-Systems and the SBB teams as the problems they 
talked about seemed like they could be addressed and solved with technology that we could build

## What it does

The app tracks your movement and uses these informations to calculate the time you take between destinations, the average velocity, 
the efficiency of your travel as well as points to reward smart __travelors__.
It also communicates with the SBB API in order to help the user prevent traffic jams and rush hours.
Long and unoptimized routes punish the user by giving him a low score whereas short routes reward him with higher scores.
Additionaly we track the usage in order to encourage attention to traffic.

## How we built it

We built an android app and used sqlite to setup a database on the device to safe the collected data.

## Challenges we ran into

The biggest problem was visualization and how random the output often seemed.

## Accomplishments that we're proud of

Managing to get all that backend to actually produce an output :D

## What we learned

Many things, such as:

- The swiss transport associations provide a complex but huge system of APIs
- Programming is life
- Sleep is secundary, team primary


## What's next for ShadowTravelor

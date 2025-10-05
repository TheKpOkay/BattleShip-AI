# BattleShip-AI
Winner of CSA Battle Ship AI Competition.
Java-based AI using a per-ship weighted rolling heatmap.

## Why Per-Ship?
By using a per-ship weighted heatmap, I was able to gain an advantage over AIs that placed ships in order. Placing ships in order leads to a detectable pattern of placement choice which was then picked up by may AI.

## Why a weighted rolling heatmap?
A weighted rolling heat map allowed me to track the previous games against an opponent. Through the thousands of games played, this setup yields a heat map that can pick up the patterns of the opponent for both placing ships and hitting ships. By tracking these statistics, my AI chose careful spots to place ships as well as optimal positions to guess, leading to hits.

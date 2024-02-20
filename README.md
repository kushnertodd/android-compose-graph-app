# android-compose-graph-app

This app allows traversing a graph by
selecting selecting nodes and arcs to
move from one node to another.
The graphics app contains two components:
The graph is static, in that it cannot be reshaped,
and in fact its structure is fixed in the code currently.

The graphic contains two types of shapes:
- Nodes
- Edges

## Graph nodes
Nodes are called 'Holds' for historical reasons that app was supposed to simulate climbing
an indoor rock climbing wall.
Each node is representing by a center point.

## Graph edges
Edges connect nodes, and each contains the line segment
representing the connection.

##  Application specification
The graph application supports the following operations:
1. Select a node, selecting that as the current
   highlighted node
1. Select a node adjacent to the selected node,
   deselecting the current node and
   selecting and highlighting that node
1. Select an edge attached to the selected node,
   selecting the node adjacent across the edge
   and deselecting the current node
1. Anywhere on the screen, swiping in the direction
   corresponding to an edge attached to the selected
   node, selecting the node adjacent across the edge
   and deselecting the current node

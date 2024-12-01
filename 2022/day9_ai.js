// Define the grid as a 2D array of characters
const grid = [  ['.', '.', '.', '.', '.', '.'],
  ['.', '.', '.', '.', '.', '.'],
  ['.', '.', '.', '.', '.', '.'],
  ['.', '.', '.', '.', '.', '.'],
  ['.', '.', '.', '.', '.', '.'],
];

// Initialize the head and tail positions
let head = [0, 0];
let tail = [0, 0];

// Define the series of motions
const motions = [  ['R', 4],
  ['U', 4],
  ['L', 3],
  ['D', 1],
  ['R', 4],
  ['D', 1],
  ['L', 5],
  ['R', 2],
];

// Create a Set to track the different tail positions
const tailPositions = new Set();

// Initialize a count variable
let count = 0;

// Loop through the motions
for (const [direction, steps] of motions) {
  // Update the head position based on the direction
  switch (direction) {
    case 'R':
      head[1] += steps;
      break;
    case 'L':
      head[1] -= steps;
      break;
    case 'U':
      head[0] -= steps;
      break;
    case 'D':
      head[0] += steps;
      break;
  }

  // Check if the head and tail are touching
  const isTouching =
    (Math.abs(head[0] - tail[0]) <= 1 && head[1] === tail[1]) ||
    (Math.abs(head[1] - tail[1]) <= 1 && head[0] === tail[0]) ||
    (Math.abs(head[0] - tail[0]) === 1 && Math.abs(head[1] - tail[1]) === 1);

  // If the head and tail are not touching, move the tail diagonally to keep up
  if (!isTouching) {
    if (head[0] < tail[0]) {
      tail[0]--;
      tail[1]++;
    } else if (head[0] > tail[0]) {
      tail[0]++;
      tail[1]--;
    } else if (head[1] < tail[1]) {
      tail[0]++;
      tail[1]++;
    } else if (head[1] > tail[1]) {
      tail[0]--;
      tail[1]--;
    }
  }

  // If the tail has moved to a new position, increment the count
  if (!tailPositions.has(`${tail[0]},${tail[1]}`)) {
    count++;
    tailPositions.add(`${tail[0]},${tail[1]}`);
  }
}

// Print the number of different positions of the tail
console.log(count);
``

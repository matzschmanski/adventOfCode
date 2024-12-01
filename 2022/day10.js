import { readFile } from '../util/util.js';
var input = readFile('/Users/geig006/Development/projects/priv/adventOfCode/2022/input/day10');
input = input
    .split("\n") // windows >:(
    .map((i) => {
        if (i.startsWith("addx")) {
            return ["noop", i];
        } else {
            return i;
        }
    })
    .flat();

let X = 1;
let crtHeight = 6;
let crtWidth = 40;
let crt = [];
let currentCrtPosition = 0;

for (let i = 0; i <= crtHeight; i++) {
    crt[i] = [];
    for (let k = 0; k <= crtWidth; k++) {
        crt[i][k] = "\x1b[31m.\x1b[0m"; // (red)
    }
}

let sums = [];

input.forEach((instruction, index) => {
    if (index === 19 || (index - 19) % 40 === 0) {
        sums.push(X * (index + 1));
    }

    // handle the crt in here
    var layer = Math.floor((index + 1) / crtWidth);

    if ([X - 1, X, X + 1].includes(currentCrtPosition)) {
        crt[layer][currentCrtPosition] = "\x1b[32;1m#\x1b[0m"; // (green, bold)
    }

    // stop handling the crt

    if (instruction.startsWith("addx")) {
        let num = parseInt(instruction.split(" ")[1]);
        X += num;
    }
    if (currentCrtPosition === 39) {
        currentCrtPosition = 0;
    } else {
        currentCrtPosition += 1;
    }
});
console.log(`Part 1: ${sums.reduce((acc, cur) => acc + cur, 0)}`);
console.log("Part 2:\n");
let output = crt.map((p) => {
    p.pop(); // unnecessary padding at the end
    p.pop();
    return p.join(" ");
});
output.pop(); // more unnecessary padding
console.log(output.join("\n"));

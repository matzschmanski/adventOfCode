import { readFile } from '../util/util.js';
var rawInput = readFile('/Users/geig006/Development/projects/priv/adventOfCode/2022/input/day9');
var startTime = performance.now()

class SnakePart {
  constructor(head,tail,spalte,zeile) {
    this.head = head;
    this.tail = tail;
    this.spalte = spalte;
    this.zeile = zeile;
  }
}

function moveSnake(snake, direction,array){
  for (let i = 0; i < array.length; i++) {
  // array[i] = array[i].map(element => {
  //   if (element !== visited) {
  //     return '.';
  //   }
  //   return element;
  // });
}
  for (var i = 0; i < snake.length; i++) {
    moveSnakePart(snake[i],direction,array);
  }
}
function printSnake(snake){
  for (var i = 0; i < snake.length; i++) {
    var hasHead =snake[i].head!=null;
    var hasTail = snake[i].tail!=null;
    console.log('Snake',hasHead,hasTail,snake[i].zeile,snake[i].spalte);
  }
}

function moveSnakePart(snakePart,direction,array){
  if(snakePart.head===null){
    // this is the head
    if(direction==='R'){
      snakePart.spalte++;
    }else if(direction==='L'){
      snakePart.spalte--;
    }else if(direction==='U'){
      snakePart.zeile--;
    }else if(direction==='D'){
      snakePart.zeile++;
    }
  }else{
    var headPart = snakePart.head;

    if(Math.abs(headPart.spalte-snakePart.spalte)>1 && headPart.zeile === snakePart.zeile){
      snakePart.spalte = (headPart.spalte+snakePart.spalte)/2;
    }
    else if(Math.abs(headPart.zeile-snakePart.zeile)>1 && headPart.spalte === snakePart.spalte){
      snakePart.zeile = (snakePart.zeile+headPart.zeile)/2;
    }
    else if(Math.abs(headPart.spalte-snakePart.spalte)>1 && Math.abs(headPart.zeile-snakePart.zeile)>1){
      snakePart.spalte = (headPart.spalte+snakePart.spalte)/2;
      snakePart.zeile = (snakePart.zeile+headPart.zeile)/2;
    }
    else if(Math.abs(headPart.zeile-snakePart.zeile)>1){
      snakePart.zeile = (snakePart.zeile+headPart.zeile)/2;
      snakePart.spalte = headPart.spalte;
    }
    else if(Math.abs(headPart.spalte-snakePart.spalte)>1){
      snakePart.spalte = (headPart.spalte+snakePart.spalte)/2;
      snakePart.zeile = headPart.zeile;
    }
}
if(snakePart.tail==null){
  array[snakePart.zeile][snakePart.spalte] = visited;
}
// else if(snakePart.head==null){
//   array[snakePart.zeile][snakePart.spalte] = 'H';
// }
// else{
//   array[snakePart.zeile][snakePart.spalte] = '-';
// }
  }

var rawInput = rawInput.split(/\r?\n/);

const snake =[]

const length = 1000;
const filler ='.'
const visited = '#';
const array = Array(length).fill(filler);
for (var i = 0; i < array.length; i++) {
  array[i] = Array(length).fill(filler);
}

var startZeile =500;
var startSpalte =500;
array[startZeile][startSpalte]='s';



var headSpalte = startSpalte;
var headZeile = startZeile;
var tailSpalte = startSpalte;
var tailZeile = startZeile;

var snakeLength =10;

for (var i = 0; i < snakeLength; i++) {
  if(i==0){
    snake[i] = new SnakePart(null,null,startSpalte,startZeile);
  }else{
    snake[i] = new SnakePart(snake[i-1],null,startSpalte,startZeile);
  }
}
for (var i = 0; i < snakeLength-1; i++) {
    snake[i].tail = snake[i+1];
}
// printSnake(snake);

var moveCount=1;
rawInput.forEach(move =>{
  var moveTime = performance.now()
   var direction = move.split(' ')[0].trim();
   var distance = move.split(' ')[1].trim();
   
  //  console.log('direction',direction,'distance',distance);
   for (var i = 0; i < distance; i++) {
    moveSnake(snake,direction,array);
   }
   var moveEndTime = performance.now()
   console.log(`move ${moveCount} took ${moveEndTime - moveTime} milliseconds`);
   moveCount++;
   
});


// for (let i = 0; i < array.length; i++) {
//   for (let j = 0; j < array[i].length; j++) {
//     process.stdout.write(array[i][j]);
//   }
//   console.log('');
// }
// console.log(array);

//count vertical


let count = 0;

// Loop through the matrix and count the '#' characters
for (let i = 0; i < array.length; i++) {
  for (let j = 0; j < array[i].length; j++) {
    if (array[i][j] === '#') {
      count++;
    }
  }
}

// Print the count
console.log(count);

var endTime = performance.now()
console.log(`Call to doSomething took ${endTime - startTime} milliseconds`);






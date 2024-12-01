var startTime = performance.now()

const length = 1000;
const lengthDebug = 0;
const wall ='#';
const sand = '+';
const air ='.';
const dropSandX = 500;
const dropSandY = 0;


const cave = Array(length).fill(air);
for (var i = 0; i < cave.length; i++) {
  cave[i] = Array(length).fill(air);
}



rawInput = require('fs').readFileSync('input.txt').toString().split('\n').map(line => line.split(' -> '));

var previousValue=null;
rawInput.forEach(array =>{
  // console.log('handling line ', array);
array.reduce(function abc(previousValue, currentValue){
  //console.log('previousValue: ',previousValue);
  //console.log('currentValue: ',currentValue);
  var oldSpalte = previousValue ? previousValue.split(',')[0]-lengthDebug : 0;
  var oldZeile = previousValue ? previousValue.split(',')[1] : 0;
  var currentSpalte = currentValue.split(',')[0]-lengthDebug;
  var currentZeile = currentValue.split(',')[1];
  if(oldSpalte === currentSpalte){
    // console.log('spalte line from',oldSpalte+':'+Math.min(oldZeile,currentZeile), 'to',oldSpalte+':'+Math.max(oldZeile,currentZeile)); 
    for (let i = Math.min(oldZeile,currentZeile); i <= Math.max(oldZeile,currentZeile); i++) {
      cave[i][currentSpalte]=wall;
    }
  }else if(oldZeile === currentZeile){
    // console.log('spalte line from', Math.min(oldSpalte,currentSpalte)+':'+oldZeile, 'to',Math.max(oldSpalte,currentSpalte)+':'+oldZeile); 
    for (let i = Math.min(oldSpalte,currentSpalte); i <= Math.max(oldSpalte,currentSpalte); i++) {
      cave[currentZeile][i]=wall;
    }
  }else{
    // console.log('?!');
  }
  return currentValue;
},'');
});
//find highest y
var lowestWall =-1;
for (var j = length-1;j>=0 && lowestWall<0; j--){
for (var i = 0; i < cave.length && lowestWall<0; i++) {
  if(cave[j][i]===wall){
    lowestWall = j;
  }
}
}
var floor = lowestWall+2;
console.log('floor at',floor);

for (var i = 0; i < cave.length; i++) {
  cave[floor][i]=wall;
}

// console.table(cave);


var endTime = performance.now()

// console.table(cave);
var countDroppedSand =0;
while(!topFilled()){
  cave[dropSandY][dropSandX] = sand;
  dropSand(cave,dropSandX,dropSandY);
  countDroppedSand++;
}
// console.table(cave);
console.log('ended with ',countDroppedSand);
console.log(`Call to doSomething took ${endTime - startTime} milliseconds`);

function dropSand(cave, sandX, sandY){
  moveSandMove(cave,sandX,sandY);
}

function moveSandMove(cave, sandX, sandY){
  var canMoveDown = checkMoveDown(cave, sandX, sandY);
  var canMoveDownLeft = checkMoveDownLeft(cave, sandX, sandY);
  var canMoveDownRight = checkMoveDownRight(cave, sandX, sandY);
  /*
   at each step trying to move down, then down-left, then down-right
   If all three possible destinations are blocked comes to rest
  */
  while(canMoveDown || canMoveDownLeft || canMoveDownRight){
    var newSandX = sandX;
    var newSandY = sandY;
    if(canMoveDown){
      newSandY++;
    }else if(canMoveDownLeft){
      newSandY++;
      newSandX--;
    }else if(canMoveDownRight){
      newSandY++;
      newSandX++;
    }
    cave[sandY][sandX] = air;
    cave[newSandY][newSandX] = sand;
    //console.log('moved sand from '+sandX+':'+sandY +' to '+newSandX+':'+newSandY);
    sandX = newSandX;
    sandY = newSandY;
    canMoveDown = checkMoveDown(cave, sandX, sandY);
    canMoveDownLeft = checkMoveDownLeft(cave, sandX, sandY);
    canMoveDownRight = checkMoveDownRight(cave, sandX, sandY);
    
  }

  // console.table(cave);
}

function checkMoveDown(cave, sandX, sandY){
  if(nextMoveTouchesVoid(sandX,sandY+1)){
    return false;
  }
  if(cave[sandY+1][sandX] ===air){
    return true;
  }
  return false;
}
function checkMoveDownLeft(cave, sandX, sandY){
  if(nextMoveTouchesVoid(sandX-1,sandY+1)){
    return false;
  }
  if(cave[sandY+1][sandX-1] ===air){
    return true;
  }
  return false;
}
function checkMoveDownRight(cave, sandX, sandY){
  if(nextMoveTouchesVoid(sandX+1,sandY+1)){
    return false;
  }
  if(cave[sandY+1][sandX+1] ===air){
    return true;
  }
  return false;
}



function checkVoid(){
  var voidTouched = false;
  for (var i = 0; i < cave.length; i++) {
    if(cave[length-1][i]===sand){
      console.log('ne');
      return true;
    }
  }
  return false;
  
}
function topFilled(){
  if(cave[dropSandY][dropSandX]===sand){
      return true;
  }
 return false;
  
}
function nextMoveTouchesVoid(nextX, nextY){
  if(nextX ===length || nextX<0){
    return true;
  }else if(nextY ===floor){
    return true;
  }
  return false;
}
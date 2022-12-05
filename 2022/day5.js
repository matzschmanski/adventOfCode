import { readFile } from './util/util.js';
var rawInput = readFile('/Users/geig006/Development/projects/priv/adventOfCode/2022/input/day5_1');

var startTime = performance.now()


var rawInput = rawInput.split(/\r?\n/);
var sum =0;

var stacksRaw= [];
var ordersRaw= [];
var secondPart = false;
rawInput.forEach(line =>  {

  
  if(line==='' || secondPart){
    secondPart = true;
    if(line!==''){
    ordersRaw.push(line);
    }
  }else{
    stacksRaw.push(line);
  }
});

var stacksTwo = [];
var stackMaxSize = stacksRaw[stacksRaw.length-1].substring(stacksRaw[stacksRaw.length-1].length-2,stacksRaw[stacksRaw.length-1].length-1);


var reg = /.{1,4}/g;
var pos=0;
var stacks= [];

for (let i = 0; i <= stacksRaw.length-2; i ++) {
  var count =0;
  // console.log('stacksRaw[i]',stacksRaw[i]);
  // while((result = reg.exec(stacksRaw[i])) !== null) {
    if(count===stackMaxSize){
      count =0;
      pos++;
    }
    stacksRaw[i].match(reg).forEach((result) => {
    if(stacks[count]== null){
      stacks[count] = [];
    }
    if(result.trim()!==''){
    stacks[count].push(result.trim());
    }
    
    count ++;
    });
}
  

// for (let i = 0; i <= stacks.length-1; i ++) {
// console.log('stacks', stacks[i]);
// }

// console.log('stacksRaw',stacksRaw);
// console.log('ordersRaw',ordersRaw);
var ordersClean = [];
for (let i = 0; i < ordersRaw.length; i ++) {

var ptrn = /move\s([^\s]*)\s/mg;
var a;
var b;
var c;
ordersRaw[i].match(ptrn).forEach((result) => {
  a=result.replace('move ','');
});
ptrn = /from\s([^\s]*)\s/mg;
ordersRaw[i].match(ptrn).forEach((result) => {
  b=result.replace('from ','');
});
ptrn = /to\s([^$]*)$/mg;
ordersRaw[i].match(ptrn).forEach((result) => {
  c = result.replace('to ','');
});
ordersClean.push(a.trim() + '@'+b.trim()+'@'+c.trim());
}



var stacksClean = []
for (let i = 0; i < stacks.length; i ++) {
  // console.log('stacks[i]',stacks[i]);
  // console.log('stacks[i].length',stacks[i].length);

  for (let j = stacks[i].length-1; j >= 0; j --) {
    if(stacksClean[i]==null){
      stacksClean[i] = [];
    }
    // console.log('stacks[i][j]',stacks[i][j]);
    stacksClean[i].push(stacks[i][j]);
  }
}

console.log('_______');
console.log('ordersClean', ordersClean);

stacks = stacksClean;
// console.log('stacks',stacks);
for (let i = 0; i < ordersClean.length; i ++) {
  var order = ordersClean[i].split('@');
  // console.log('order', order);
  var popArray = [];
  for (let j = 0; j < order[0]; j ++) {
    popArray.push(stacks[order[1]-1].pop());
    //console.log('test',test);
    
  }

  //reverse poparray
var pushArray = [];
for (let j = popArray.length-1; j >=0; j --) {
  pushArray.push(popArray[j]);
}


  //push poparray
  for (let j = 0; j < pushArray.length; j ++) {
  stacks[order[2]-1].push(pushArray[j]);
  }

  // console.log('stacks',stacks);
  //console.log('stacksClean',stacksClean);
  // for (let j = 0; j < order.length; j ++) {

  // }
}

var strong ="";
for (let i = 0; i < stacks.length; i ++) {
  console.log('stacks[i][stacks[i].length-1]',stacks[i][stacks[i].length-1]);
  strong+=stacks[i][stacks[i].length-1].substring(1,2);
}
console.log('string', strong);





var endTime = performance.now()
console.log(`Call to doSomething took ${endTime - startTime} milliseconds`)
import { readFile } from '../util/util.js';
var rawInput = readFile('/Users/geig006/Development/projects/priv/adventOfCode/2022/input/day8');
var startTime = performance.now()

var rawInput = rawInput.split(/\r?\n/);


function checkScenic(zeile,spalte, array){
  var isDebug = false;
  var scenic = [];
  var count =0;
if(isDebug){
  console.log('zeile',zeile,'spalte',spalte,'wert',array[zeile][spalte],'nach links');
}
  for (var i = spalte-1; i >= 0; i--) {
    count ++;
    if(array[zeile][i] == null || array[zeile][i]>=array[zeile][spalte]){
       break;
    }
  }
  if(isDebug){
  console.log('links',count);
  }
  scenic.push(count);
  count=0;
  if(isDebug){
  console.log('zeile',zeile,'spalte',spalte,'wert',array[zeile][spalte],'nach rechts');
  }
  for (var i = spalte+1; i < array[spalte].length; i++) {
    count ++;
    if(array[zeile][i] == null || array[zeile][i]>=array[zeile][spalte]){
       break;
    }
    if(isDebug){
    console.log('array['+zeile+']['+i+']',array[zeile][i], 'i'+i,'count',count);
    }
  }
  if(isDebug){
  console.log('rechts',count);
  }
  scenic.push(count);
  count=0;
  if(isDebug){
  console.log('zeile',zeile,'spalte',spalte,'wert',array[zeile][spalte],'nach oben');
  }
  for (var i = zeile-1; i >= 0; i--) {
    count ++;
    if(array[i][spalte] ==null || array[i][spalte]>=array[zeile][spalte]){
     break;
    }
  }
  if(isDebug){
  console.log('oben',count);
  }
  scenic.push(count);
  count=0;
  if(isDebug){
  console.log('zeile',zeile,'spalte',spalte,'wert',array[zeile][spalte],'nach unten');
  }
  for (var i = zeile+1; i < array.length; i++) {
      count ++;
      if(array[i][spalte] >= array[zeile][spalte]){
        break;
    }
  }
  if(isDebug){
  console.log('unten',count);
  }
  scenic.push(count);
  if(isDebug){
  console.log('scenic', scenic);
  }
  var result =scenic.reduce( (a, b) => a * b );
  if(isDebug){
  console.log('result', result);
  }
  
  return result;

}

function checkChoord(x,y, array){
  var top = true;
  var bottom = true;
  var left = true;
  var right = true;
  
  for (var i = x-1; i >= 0; i--) {
    if(array[i][y] ==null || array[i][y]>=array[x][y]){
      top=false;
    }
  }
  for (var i = x+1; i < array.length; i++) {
    if(array[i][y] ==null || array[i][y]>=array[x][y]){
      bottom=false;
    }
  }

  for (var i = y-1; i >= 0; i--) {
    if(array[x][i] ==null || array[x][i]>=array[x][y]){
      left=false;
    }
  }
  for (var i = y+1; i < array.length; i++) {
    if(array[x][i] ==null ||array[x][i]>=array[x][y]){
      right=false;
    }
  }
  return left||top||right||bottom;
}


var currentFolder=''


var array = []

var count =0;
rawInput.forEach(line =>  {
  
var lineSplit = line.split('');
  var innerArray = [];
  for (var i = 0; i < lineSplit.length; i++) {
    innerArray[i] = parseInt(lineSplit[i]);
  }
  array[count] = innerArray;
  count++;
});
console.log(array);

//count vertical

var countVisible =0;

var coords = new Map();

for (var i = 0; i < array.length; i++) {
  for (var j = 0; j < array[i].length; j++) {
    var key = i+':'+j;
    if(checkChoord(i,j,array)){
      coords.set(key,key);
    }
  }
  

}


// console.log('coords',coords);

// var spalteTest=2;
// var zeileTest=3;
// console.log(spalteTest+':'+zeileTest,'value='+array[zeileTest][spalteTest],checkScenic(zeileTest,spalteTest, array));
var highest =0;
for (var i = 0; i < array.length; i++) {
  for (var j = 0; j < array[i].length; j++) {
    var spalte=j;
    var zeile=i;
    var height = checkScenic(zeile,spalte, array);
    // console.log(zeile+':'+spalte, height);
    if(height>highest){
      highest = height;
    }
  }
}

console.log('highest',highest);
var endTime = performance.now()
console.log(`Call to doSomething took ${endTime - startTime} milliseconds`);






const { jets } = require("./parse");

function formHitsGround(form, y, x, cave){
    let hitGround = false;
    for (var j = 0; j < form[form.length-1].length; j++) {
        if(y===height-1){
            hitGround = true;
        }
        else if(cave[y+form.length-1][x+j] === rock && form[form.length-1][j] !==air){
            hitGround = true;
        }
    }
    if(hitGround){
        console.log('hitGround at y=',y);
    }
    return hitGround;
}

function moveWithGas(form,y,x,cave, jetCount){
    if('>'===jets[jetCount]){
        if(x<cave.length-form.length){
            x++;
        }
    } else if('<'===jets[jetCount]){
        if(x>form.length){
            x--;
        }
    } else{
        console.log('wtf');
    }
    for (var i = 0; i < form.length; i++) {
        for (var j = 0; j < form[i].length; j++) {
            cave[i+y][j+x] = form[i][j];
        }
    }
    
    
}
function clearCave(cave){
    return JSON.parse(JSON.stringify(Array(cave.length).fill(Array(length).fill(air))));
 }
function dropForm(form, y, x, cave){
    console.log('y',y);
    console.log('x',x);
    cave = paintForm(form,y,x,cave);
    console.table(cave);
    cave = moveForm(form,y,x,cave,0);
    cave = sinkForm(form,y,x,cave);
    console.table(cave);
    console.log('y',y);
    console.log('x',x);
    return cave;
}

function paintForm(form, y, x, cave){
    cave = clearCave(cave);
    for (var i = 0; i < form.length; i++) {
        cave[y+i].splice(x,form.length,...form[i]);
    }
    return JSON.parse(JSON.stringify(cave));
}

function moveForm(form,y,x,cave, jetCount){
        cave = clearCave(cave);
        if('>'===jets[jetCount]){
            if(x<cave.length-form.length){
                x++;
            }
        } else if('<'===jets[jetCount]){
            if(x>form.length){
                x--; 
            }
        } else{
            console.log('wtf');
        }
        cave = paintForm(form,y,x,cave);
        return JSON.parse(JSON.stringify(cave));
}
function sinkForm(form,y,x,cave){
    let hitGround = false;
    hitGround=formHitsGround(form,y,x,cave);
    if(hitGround){
        for (var i = 0; i < cave.length; i++) {
            for(var j =0; j<cave[i].length; j++){
                if(cave[i][j]===movingRock){
                    cave[i][j]=rock;
                }
                
            }
        }
    }else{
        y++;
        cave = paintForm(form,y,x,cave);
    }
    return JSON.parse(JSON.stringify(cave));
}

console.time("ExecutionTime");

const length = 7;
const height = 4;
const rock ='#';
const movingRock = '@';
const air ='.';
const dropY =0;
const dropX =2;

let forms = [];
const formOne = [...Array(1)].map(() => [...Array(4)].fill(movingRock));
const formTwo = [...Array(3)].map(() => [...Array(3)].fill(movingRock));
formTwo[0][0] = air;
formTwo[0][2] = air;
formTwo[2][0] = air;
formTwo[2][2] = air;
const formThree = [...Array(3)].map(() => [...Array(3)].fill(movingRock));
formThree[0][0] = air;
formThree[0][1] = air;
formThree[1][0] = air;
formThree[1][1] = air;
const formFour = [...Array(4)].map(() => [...Array(1)].fill(movingRock));
const formFive = [...Array(2)].map(() => [...Array(2)].fill(movingRock));
forms.push(formOne);
forms.push(formTwo);
forms.push(formThree);
forms.push(formFour);
forms.push(formFive);

let cave = Array(height).fill(Array(length).fill(air));


// let fillerArray = Array(length).fill(air);
// // cave = dropForm(forms[0], dropY, dropX, cave);
cave.splice(0,0,Array(length).fill(air));
cave.splice(0,0,Array(length).fill(air));
cave.splice(0,0,Array(length).fill(air));
cave = dropForm(forms[1], dropY, dropX, cave);

console.timeEnd("ExecutionTime");
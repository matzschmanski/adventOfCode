var startTime = performance.now()

input=require('fs').readFileSync('/Users/geig006/Development/projects/priv/adventOfCode/2022/input/day13').toString().split('\r').join('').split('\n\n')
    .map(l=>l.split('\n').map(x=>JSON.parse(x)))


function checkValue(a,b){
    if(!Array.isArray(a) && !Array.isArray(b))  return a - b
    else{
        if(!Array.isArray(a)) a = [a]
        if(!Array.isArray(b)) b = [b]
        for (var i = 0; i < Math.min(a.length,b.length); i++)
            if ((res=checkValue(a[i],b[i]))!=0) return res
        return a.length - b.length
    }
}
console.log("PART 1:",input.reduce((sum,l,i)=>sum+(checkValue(l[0],l[1])>0?0:(i+1)),0))

input.push([[[2]],[[6]]])
part2 = input.flat().sort((a,b)=>checkValue(a,b)).map(x=>x.toString())
console.log("PART 2:",(part2.indexOf('2')+1)*(part2.indexOf('6')+1))
var endTime = performance.now()
console.log(`Call to doSomething took ${endTime - startTime} milliseconds`);






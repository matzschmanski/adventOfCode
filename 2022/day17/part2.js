function maxPressureReleased (input, startTime) {
  const score = search(input, startTime)
  return score[0][1]
}

function maxPressureReleased2 (input, startTime) {
  const score = search(input, startTime)
  let max = 0
  for (let j = 1; j < score.length; j++) {
    for (let i = 0; i < j; i++) {
      if (score[i][1] * 2 < max) break
      const hashA = score[i][0]
      const hashB = score[j][0]
      if (hashA & hashB) continue
      const total = score[i][1] + score[j][1]
      if (total > max) max = total
    }
  }
  return max
}

function search (input, startTime) {
  const valves = getValves(input)
  const openable = input.filter(row => row.rate > 0)
  const shortestPath = getShortestPath(valves, openable)

  const score = []
  const unvisited = []
  unvisited.push([0, 'AA', startTime, 0])

  while (unvisited.length > 0) {
    const [visited, next, time, released, extras] = unvisited.pop()
    openable.forEach(row => {
      if (visited & row.hash) return
      score.push([visited, released])
      const distance = shortestPath[next][row.from]
      const nextTime = time - distance - 1
      if (nextTime > 0) {
        unvisited.push([
          visited + row.hash,
          row.from,
          nextTime,
          released + nextTime * row.rate,
          extras
        ])
      }
    })
  }

  return score.sort((a, b) => b[1] - a[1])
}

function getShortestPath (valves, openable) {
  function findShortestPath (start) {
    const visited = {}
    const unvisited = []
    unvisited.push([valves[start], 0])
    while (unvisited.length > 0) {
      const [next, steps] = unvisited.shift()
      if (next.from in visited) {
        if (steps >= visited[next.from]) continue
        else visited[next.from] = steps
      } else {
        visited[next.from] = steps
      }
      Object.keys(next.to).forEach(id =>
        unvisited.push([valves[id], steps + next.to[id]])
      )
    }
    delete visited[start]
    return visited
  }

  const shortest = {}
  shortest.AA = findShortestPath('AA')
  openable.forEach(row => {
    shortest[row.from] = findShortestPath(row.from)
  })
  return shortest
}

function getValves (input) {
  const valves = {}
  let hash = 1
  input.forEach(row => {
    valves[row.from] = row
    if (row.rate > 0) {
      row.hash = hash
      hash *= 2
    }
  })

  function preprocessInputRowTo (row, path = []) {
    if (!Array.isArray(row.to)) return row.to
    const to = {}
    row.to.forEach(id => {
      if (path.includes(id)) return
      const next = valves[id]
      const steps =
        next.rate > 0
          ? { [id]: 0 }
          : preprocessInputRowTo(next, [...path, row.from])
      Object.keys(steps).forEach(id => {
        if (id in to) to[id] = Math.min(to[id], steps[id] + 1)
        else to[id] = steps[id] + 1
      })
    })
    delete to[row.from]
    return to
  }

  input.forEach(row => {
    row.to = preprocessInputRowTo(row)
  })

  return valves
}

function parse (line) {
  const matched = line.match(
    /^Valve ([A-Z]+) has flow rate=(\d+); tunnels? leads? to valves? ([A-Z, ]+)$/
  )
  return {
    from: matched[1],
    to: matched[3].split(', '),
    rate: +matched[2]
  }
}

const test = `
Valve GV has flow rate=23; tunnel leads to valve WO
Valve TS has flow rate=0; tunnels lead to valves IG, TX
Valve UC has flow rate=0; tunnels lead to valves XJ, VZ
Valve TJ has flow rate=0; tunnels lead to valves GJ, YV
Valve KF has flow rate=0; tunnels lead to valves QY, VP
Valve PO has flow rate=0; tunnels lead to valves YF, VP
Valve CV has flow rate=0; tunnels lead to valves VB, QK
Valve NK has flow rate=6; tunnels lead to valves MI, QY, DO, QJ, YH
Valve IG has flow rate=4; tunnels lead to valves MI, FP, OP, UV, TS
Valve KN has flow rate=0; tunnels lead to valves RF, CY
Valve KR has flow rate=0; tunnels lead to valves VP, DI
Valve VZ has flow rate=19; tunnel leads to valve UC
Valve MW has flow rate=0; tunnels lead to valves UZ, VB
Valve LJ has flow rate=25; tunnels lead to valves XJ, LI
Valve DI has flow rate=0; tunnels lead to valves KR, AA
Valve TO has flow rate=12; tunnels lead to valves TG, PB, BZ
Valve CG has flow rate=0; tunnels lead to valves VP, TX
Valve GJ has flow rate=0; tunnels lead to valves QL, TJ
Valve UZ has flow rate=0; tunnels lead to valves MW, VP
Valve RF has flow rate=16; tunnels lead to valves RD, KN, AU
Valve CY has flow rate=0; tunnels lead to valves KN, YV
Valve AA has flow rate=0; tunnels lead to valves UV, VS, NB, XO, DI
Valve YV has flow rate=11; tunnels lead to valves CY, PW, TJ
Valve VS has flow rate=0; tunnels lead to valves QK, AA
Valve TX has flow rate=14; tunnels lead to valves RM, CG, TS, DM, YH
Valve SB has flow rate=0; tunnels lead to valves YF, BZ
Valve QY has flow rate=0; tunnels lead to valves NK, KF
Valve PB has flow rate=0; tunnels lead to valves HP, TO
Valve YF has flow rate=20; tunnels lead to valves DM, SB, PO
Valve TG has flow rate=0; tunnels lead to valves RM, TO
Valve UV has flow rate=0; tunnels lead to valves IG, AA
Valve XJ has flow rate=0; tunnels lead to valves LJ, UC
Valve DM has flow rate=0; tunnels lead to valves YF, TX
Valve PW has flow rate=0; tunnels lead to valves YV, LI
Valve RD has flow rate=0; tunnels lead to valves QL, RF
Valve OM has flow rate=0; tunnels lead to valves QK, OP
Valve RM has flow rate=0; tunnels lead to valves TX, TG
Valve SH has flow rate=0; tunnels lead to valves AU, HP
Valve LI has flow rate=0; tunnels lead to valves PW, LJ
Valve FP has flow rate=0; tunnels lead to valves IG, VB
Valve BZ has flow rate=0; tunnels lead to valves SB, TO
Valve DO has flow rate=0; tunnels lead to valves NK, VB
Valve WO has flow rate=0; tunnels lead to valves QK, GV
Valve MI has flow rate=0; tunnels lead to valves IG, NK
Valve QK has flow rate=10; tunnels lead to valves VS, OM, WO, CV
Valve OP has flow rate=0; tunnels lead to valves IG, OM
Valve AU has flow rate=0; tunnels lead to valves SH, RF
Valve QJ has flow rate=0; tunnels lead to valves NK, XO
Valve VP has flow rate=8; tunnels lead to valves PO, CG, KF, KR, UZ
Valve HP has flow rate=17; tunnels lead to valves SH, PB
Valve XO has flow rate=0; tunnels lead to valves QJ, AA
Valve QL has flow rate=15; tunnels lead to valves RD, GJ
Valve NB has flow rate=0; tunnels lead to valves VB, AA
Valve VB has flow rate=7; tunnels lead to valves DO, CV, MW, NB, FP
Valve YH has flow rate=0; tunnels lead to valves NK, TX
`.trim().split('\n').map(parse)

console.log(maxPressureReleased(test, 30))
console.log(maxPressureReleased2(test, 26))
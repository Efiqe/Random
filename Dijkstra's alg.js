const COLS = 3;
const ROWS = 3;
let grid = [];



class Node {
    constructor(y, x) {
        this.y = y;
        this.x = x;
        this.weight = Infinity;
        this.searched = false;
    }
}


class QElement {
    constructor(element, priority) {
        this.element = element;
        this.priority = priority;
    }
} 


class PriorityQueue {
    constructor() {
        this.items = [];
    }

    enqueue(element, priority) {
        let qElement = new QElement(element, priority);
        let contain = false;
        
        for(let i = 0; i < this.items.length; i++) {
            if(this.items[i].priority > qElement.priority) {
                this.items.splice(i, 0, qElement);
                contain = true;
                break;
            }
        }

        if(!contain) {
            this.items.push(qElement);
        }
    }

    dequeue() {
        if(this.isEmpty()) {
            return "Underflow"
        } else {
            return this.items.shift()
        }
    }

    isEmpty() {
        return this.items.length === 0;
    }
}


function equal(a, b) {
    if (a === b) return true;
    if (a == null || b == null) return false;
    if (a.length !== b.length) return false;
  
    for (var i = 0; i < a.length; ++i) {
      if (a[i] !== b[i]) return false;
    }
    return true;
}


const findNeighbors = (grid, cord) => {
    let neighbors = [];

    //Nahoru
    if(cord[0] + 1 < grid.length) {
        neighbors.push([cord[0] + 1, cord[1]])
    }

    //Dolu
    if(cord[0] - 1 >= 0) {
        neighbors.push([cord[0] - 1, cord[1]])
    }

    //Doleva
    if(cord[1] - 1 >= 0) {
        neighbors.push([cord[0], cord[1] - 1])
    }

    //Doprava
    if(cord[1] + 1 < grid[0].length) {
        neighbors.push([cord[0], cord[1] + 1])
    }
    
    fillNeighbors(neighbors);
    
    return neighbors;
}


const fillNeighbors = (neigh) => {
    neigh.forEach((el, idx, arr) => {
        let y = el[0];
        let x = el[1];
        arr[idx] = new Node(y, x);
    });
}


const createGrid = () => {
    for(let y = 0; y < COLS; y++) {
        grid[y] = [];
        for(let x = 0; x < ROWS; x++) {
            grid[y][x] = new Node(y, x)
        }
    }
}


const dijkstra = (grid, st) => {
    const PQ = new PriorityQueue()
    let dict = {};
    st.weight = 0;
    st.searched = true;
    PQ.enqueue(st, st.weight);

    while(PQ.items.length > 0) {
        let curr = PQ.dequeue();
        let current = curr.element;

        if(current === end) {
            console.log("Found")
        } else {
            let neighbors = findNeighbors(grid, [current.y, current.x]);

            neighbors.forEach(el => {
                let mem = grid[el.y][el.x];

                if(grid[el.y][el.x].searched === false) {
                    mem.searched = true;
                    current.weight = current.weight + 1;
                    mem.weight = current.weight;
                    PQ.enqueue(mem, mem.weight);
                    dict[[el.y, el.x]] = [current.y, current.x]
                }
            })
        } 
    }   
    
    return dict;
}

const showPath = (dict, start, end) => {
    let curPos = end;
    const result = [];

    while(!equal(curPos, start)) {
        result.push(curPos);
        curPos = dict[curPos];
    }

    result.push(start);

    return result;
}




createGrid();
const start = grid[0][0];
const end = grid[2][2];
const dict = dijkstra(grid, start);
const path = showPath(dict, [start.y, start.x], [end.y, end.x])
// console.log(dict)
console.log(path.reverse())
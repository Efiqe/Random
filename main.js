const rows = 3;
const cols = 3;
const grid = [];

function Node(x ,y) {
    this.x = x;
    this.y = y;
    this.searched = false;
}

const createGrid = () => { 
    for(let i = 0; i < rows; i++) {
        grid[i] = new Array()
        for(let j = 0; j < cols; j++) {
            grid[i][j] = new Node(i,j)
        }
    }

    return grid
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
        let x = el[0];
        let y = el[1];
        arr[idx] = new Node(x, y);
    });
};

createGrid()    

const start = grid[0][0]
const end = grid[2][2]
 
const BFS = (grid, start) => {
    let queue = []
    let queueCopy = queue;
    start.searched = true
    queue.push(start)


    while(queue.length > 0) {
        let current = queue.shift();
        visited.push(current);

        if(current === end) {
            console.log(visited)
            console.log("Found");
            break;
        } else {
            let neighbors = findNeighbors(grid, [current.x, current.y])
        
            neighbors.forEach(el => {
                let mem = grid[el.x][el.y];
                if(grid[el.x][el.y].searched === false) {
                    mem.searched = true;
                    queue.push(mem);
                };
            });    
        }
    };
        
};

BFS(grid, start)
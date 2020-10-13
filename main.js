const rows = 5
const cols = 5
const grid = new Array()

function Node(x ,y) {
    this.searched = false;
    this.x = x;
    this.y = y;
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
    // const row = 2;
    // const col = 2;
    
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
    
    // for(let i = 0; i < row; i++) {
    //     for(let j = 0; j < col; j++) {
    //         neighbors[i][j] = new Node(i, j);
      
    //     };
    // };

    // console.log(neighbors)

    fillNeighbors(neighbors);

    return neighbors;
}

const fillNeighbors = (neigh) => {
    neigh.forEach((el, idx, arr) => {
        // console.log(el[0])
        let x = el[0];
        let y = el[1];
        arr[idx] = new Node(x, y);
    });
};

createGrid()    
// console.log(grid)



const start = grid[0][0]
const end = grid[4][4]

const BFS = (grid, start) => {
    let queue = []
    start.searched = true
    queue.push(start)

    // console.log(queue)

    while(queue.length > 0) {
        let current = queue.shift();
        // console.log(current)
        // console.log(end)
        if(current === end) {
            console.log("Found");
            break;
        };

        let neighbors = findNeighbors(grid, [current.y, current.x])
        
        // console.log(neighbors)

        neighbors.forEach(el => {
            // console.log("x: " + el.x)
            // console.log("y: " + el.y)
            // console.log(grid[el.x][el.y])
            let mem = grid[el.x][el.y];
            if(grid[el.x][el.y].searched === false) {
                queue.push(mem);
                // console.log(queue)
            };
        });
    }
        
}

BFS(grid, start)
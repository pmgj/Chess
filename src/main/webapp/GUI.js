import ConnectionType from "./ConnectionType.js";
import State from "./State.js";
import Cell from "./Cell.js";
import Winner from "./Winner.js";
import Player from "./Player.js";

class GUI {
    constructor() {
        this.ws = null;
        this.player = null;
        this.table = null;
        this.origin = null;
        this.closeCodes = { ENDGAME: { code: 4000, description: "End of game." }, ADVERSARY_QUIT: { code: 4001, description: "The opponent quit the game" } };
    }
    writeResponse(text) {
        let message = document.getElementById("message");
        message.innerHTML = text;
    }
    printBoard(board) {
        let tbody = document.querySelector("tbody");
        tbody.innerHTML = "";
        let transMatrix = board;
        if (this.player === Player.PLAYER2) {
            transMatrix = Array(board.length).fill().map(() => Array(board[0].length).fill());
            for (let i = 0, rows = board.length; i < rows; i++) {
                for (let j = 0, cols = board[i].length; j < cols; j++) {
                    transMatrix[i][j] = board[rows - i - 1][cols - j - 1];
                }
            }
            board = transMatrix;
        }
        for (let i = 0; i < transMatrix.length; i++) {
            let tr = document.createElement("tr");
            for (let j = 0; j < transMatrix[i].length; j++) {
                let td = document.createElement("td");
                if (transMatrix[i][j].state !== State.EMPTY) {
                    let img = document.createElement("img");
                    img.src = `images/${transMatrix[i][j].piece}-${transMatrix[i][j].state}.svg`;
                    // img.ondragstart = drag;
                    td.appendChild(img);
                }
                td.onclick = this.play.bind(this);
                // td.ondragover = allowDrop;
                // td.ondrop = drop;
                tr.appendChild(td);
            }
            tbody.appendChild(tr);
        }
        // changeMessage();
    }
    piecePlayer(row, col) {
        if (col === undefined) {
            col = row.cellIndex;
            row = row.parentNode.rowIndex;
        }
        let cell = this.table.rows[row].cells[col];
        let img = cell.firstChild;
        if (img) {
            if (img.src.indexOf("PLAYER1") !== -1) {
                return State.PLAYER1;
            } else {
                return State.PLAYER2;
            }
        } else {
            return State.EMPTY;
        }
    }
    unselectCells() {
        let rows = this.table.rows;
        for (let i = 0; i < rows.length; i++) {
            let row = rows[i];
            for (let j = 0; j < row.cells.length; j++) {
                let cell = row.cells[j];
                cell.className = "";
            }
        }
    }
    getTableData({ x, y }) {
        return this.table.rows[x].cells[y];
    }
    movePiece(begin, end) {
        if (!begin || !end) {
            return;
        }
        let bTD = this.getTableData(this.getCorrectCell(begin));
        let eTD = this.getTableData(this.getCorrectCell(end));
        if (eTD.firstChild) {
            eTD.removeChild(eTD.firstChild);
        }
        let img = bTD.firstChild;
        eTD.appendChild(img);
    }
    coordinates(tableData) {
        return new Cell(tableData.parentNode.rowIndex, tableData.cellIndex);
    }
    play(evt) {
        let td = evt.currentTarget;
        if (this.origin === null) {
            this.ws.send(JSON.stringify({ beginCell: this.getCorrectCell(this.coordinates(td)) }));
            this.origin = td;
        } else {
            this.ws.send(JSON.stringify({ beginCell: this.getCorrectCell(this.coordinates(this.origin)), endCell: this.getCorrectCell(this.coordinates(td)) }));
            this.origin = null;
        }
    }
    unsetEvents() {
        let tds = document.querySelectorAll("td");
        tds.forEach(td => td.onclick = undefined);
    }
    getCorrectCell(cell) {
        return this.player === Player.PLAYER1 ? cell : this.getRotatedCell(cell);
    }
    getRotatedCell({ x, y }) {
        return new Cell(8 - x - 1, 8 - y - 1);
    }
    showPossibleMoves(cells) {
        let moves = cells;
        if (this.player === Player.PLAYER2) {
            moves = cells.map(c => this.getRotatedCell(c));
        }
        for (let cell of moves) {
            let td = this.getTableData(cell);
            td.className = "selected";
        }
    }
    hidePossibleMoves() {
        let tds = document.querySelectorAll("td");
        tds.forEach(td => td.className = "");
    }
    endGame(closeObj, winner) {
        this.unsetEvents();
        this.ws.close(closeObj.code, closeObj.description);
        this.ws = null;
        this.setButtonText(true);
        this.writeResponse(`Game Over! ${(winner === "DRAW") ? "Draw!" : (winner === this.player ? "You win!" : "You lose!")}`);
    }
    onMessage(evt) {
        let data = JSON.parse(evt.data);
        console.log(data);
        let game = data.game;
        switch (data.type) {
            case ConnectionType.OPEN:
                this.player = data.turn;
                this.writeResponse("Waiting for opponent.");
                break;
            case ConnectionType.CREATE_BOARD:
                this.printBoard(game.board);
                this.writeResponse(this.player === game.turn ? "It's your turn." : "Wait for your turn.");
                break;
            case ConnectionType.SHOW_MOVES:
                this.showPossibleMoves(data.possibleMoves);
                break;
            case ConnectionType.MOVE_PIECE:
                if (game.winner === Winner.NONE) {
                    this.movePiece(data.beginCell, data.endCell);
                    if (game.enPassant) {
                        let cell = this.getTableData(this.getCorrectCell(game.enPassant));
                        cell.removeChild(cell.firstChild);
                    }
                    if (game.castling) {
                        this.movePiece(game.castling[0], game.castling[1]);
                    }
                    if (this.player === game.turn && game.promotionCell) {
                        let select = document.querySelector("select");
                        for (let piece of game.promotionList) {
                            let option = document.createElement("option");
                            option.textContent = piece;
                            select.appendChild(option);
                        }
                        select.className = "show";
                        select.onchange = () => {
                            this.ws.send(JSON.stringify({ promote: select.value }));
                            select.innerHTML = "";
                            select.className = "hide";
                        };
                    }
                    let check = game.check ? "Check!" : "";
                    this.writeResponse(this.player === game.turn ? `${check} It's your turn.` : `${check} Wait for your turn.`);
                } else {
                    this.endGame(this.closeCodes.ENDGAME, game.winner);
                    this.movePiece(data.beginCell, data.endCell);
                }
                this.hidePossibleMoves();
                break;
            case ConnectionType.PROMOTED_PIECE:
                let promotedPiece = game.promotedPiece;
                let cell = this.getCorrectCell(game.promotionCell);
                let td = this.getTableData(cell);
                td.innerHTML = `<img src="images/${promotedPiece}-${game.turn === Player.PLAYER1 ? Player.PLAYER2 : Player.PLAYER1}.svg" alt="" />`;
                let check = game.check ? "Check!" : "";
                this.writeResponse(this.player === game.turn ? `${check} It's your turn.` : `${check} Wait for your turn.`);
                break;
            case ConnectionType.QUIT_GAME:
                this.endGame(1000, data.turn);
                break;
        }
    }
    startGame() {
        if (this.ws) {
            this.endGame(this.closeCodes.ADVERSARY_QUIT);
        } else {
            this.ws = new WebSocket(`ws://${document.location.host}${document.location.pathname}chess`);
            this.ws.onmessage = this.onMessage.bind(this);
            this.table = document.querySelector("table");
            this.setButtonText(false);
        }
    }
    setButtonText(on) {
        let button = document.querySelector("input[type='button']");
        button.value = on ? "Start" : "Quit";
    }
    init() {
        let button = document.querySelector("input[type='button']");
        button.onclick = this.startGame.bind(this);
        this.setButtonText(true);
    }
}
let gui = new GUI();
gui.init();
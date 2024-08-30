"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    Object.defineProperty(o, k2, { enumerable: true, get: function() { return m[k]; } });
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
const ws_1 = __importStar(require("ws"));
const PORT = process.env.PORT ? parseInt(process.env.PORT) : 8080;
let users = [];
console.log(`Listening on port ${PORT}`);
const wss = new ws_1.WebSocketServer({ port: PORT });
wss.on('connection', (ws) => {
    console.log('ws connected');
    ws.on('message', (data) => {
        const raw_data = data.toString();
        try {
            const parsed_data = JSON.parse(raw_data);
            switch (parsed_data.messageType) {
                case 'register':
                    users.push({ ws, nick: parsed_data.data, isAlive: true });
                    broadcast(JSON.stringify({ messageType: 'users', dataArray: users.map((u) => u.nick) }));
                    break;
                case 'message':
                    const sender = users.find((u) => u.ws === ws);
                    if (sender) {
                        broadcast(JSON.stringify({
                            messageType: 'message',
                            data: JSON.stringify({
                                from: sender.nick,
                                message: parsed_data.data,
                                time: Date.now(),
                            }),
                        }));
                    }
            }
        }
        catch (e) {
            console.log('Error in message', e);
        }
    });
});
const interval = setInterval(function ping() {
    const current_clients = Array.from(wss.clients);
    const updated_users = users.filter((u) => current_clients.includes(u.ws));
    if (updated_users.length !== users.length) {
        users = updated_users;
        broadcast(JSON.stringify({ messageType: 'users', dataArray: users.map((u) => u.nick) }));
    }
}, 5000);
const broadcast = (data) => {
    wss.clients.forEach((client) => {
        if (client.readyState === ws_1.default.OPEN) {
            client.send(data);
        }
    });
};

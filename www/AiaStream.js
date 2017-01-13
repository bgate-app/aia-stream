var exec = require('cordova/exec');

var AiaStream = function() {

}

AiaStream.prototype.initAll = function() {
    return new Promise((resolve, reject) => {
        exec(function(data) { resolve(data); }, function(data) { reject(data); }, "AiaStream", "initAll", []);
    });
};
AiaStream.prototype.initBroadcast = function() {
    return new Promise((resolve, reject) => {
        exec(function(data) { resolve(data); }, function(data) { reject(data); }, "AiaStream", "initBroadcast", []);
    });
};

AiaStream.prototype.startCameraPreview = function() {
    return new Promise((resolve, reject) => {
        exec(function(data) { resolve(data); }, function(data) { reject(data); }, "AiaStream", "startCameraPreview", []);
    });
};

AiaStream.prototype.stopCameraPreview = function() {
    return new Promise((resolve, reject) => {
        exec(function(data) { resolve(data); }, function(data) { reject(data); }, "AiaStream", "stopCameraPreview", []);
    });
};
AiaStream.prototype.startBroadcast = function(sucess, error, options) {
    exec(success, error, "AiaStream", "startBroadcast", [options]);
};

AiaStream.prototype.stopBroadcast = function() {
    return new Promise((resolve, reject) => {
        exec(function(data) { resolve(data); }, function(data) { reject(data); }, "AiaStream", "stopBroadcast", []);
    });
};
AiaStream.prototype.stopBroadcastAndPreview = function() {
    return new Promise((resolve, reject) => {
        exec(function(data) { resolve(data); }, function(data) { reject(data); }, "AiaStream", "stopBroadcastAndPreview", []);
    });
};
AiaStream.prototype.switchCamera = function() {
    return new Promise((resolve, reject) => {
        exec(function(data) { resolve(data); }, function(data) { reject(data); }, "AiaStream", "switchCamera", []);
    });
};

AiaStream.prototype.setFilter = function(options) {
    return new Promise((resolve, reject) => {
        exec(function(data) { resolve(data); }, function(data) { reject(data); }, "AiaStream", "setFilter", [options]);
    });
};

AiaStream.prototype.setAudioEnable = function(options) {
    return new Promise((resolve, reject) => {
        exec(function(data) { resolve(data); }, function(data) { reject(data); }, "AiaStream", "setAudioEnable", [options]);
    });
};



AiaStream.prototype.initVideoPlayer = function() {
    return new Promise((resolve, reject) => {
        exec(() => { resolve(); }, () => { reject(); }, "AiaStream", "initVideoPlayer", []);
    });
};

AiaStream.prototype.stopVideoPlayer = function() {
    return new Promise((resolve, reject) => {
        exec(() => { resolve(); }, () => { reject(); }, "AiaStream", "stopVideoPlayer", []);
    });
};
AiaStream.prototype.stopAll = function() {
    return new Promise((resolve, reject) => {
        exec(() => { resolve(); }, () => { reject(); }, "AiaStream", "stopAll", []);
    });
};

AiaStream.prototype.reloadVideo = function() {
    return new Promise((resolve, reject) => {
        exec(() => { resolve(); }, () => { reject(); }, "AiaStream", "reloadVideo", []);
    });
};

AiaStream.prototype.playVideo = function(url) {
    return new Promise((resolve, reject) => {
        exec(() => { resolve(); }, () => { reject(); }, "AiaStream", "playVideo", [{
            url: url
        }]);
    });
};

AiaStream.prototype.playDefaultVideo = function(url) {
    return new Promise((resolve, reject) => {
        exec(() => { resolve(); }, () => { reject(); }, "AiaStream", "playDefaultVideo", [{
            url: url
        }]);
    });
};

var aiaStream = new AiaStream();

if (!window.plugins) {
    window.plugins = {};
}
window.plugins.aiaStream = aiaStream;

module.exports = aiaStream;
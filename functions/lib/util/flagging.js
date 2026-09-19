"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.computeFlag = computeFlag;
function computeFlag(rawValue, parameter, sex) {
    if (parameter.inputType === 'text')
        return 'NA';
    const numericValue = parseFloat(rawValue);
    if (isNaN(numericValue))
        return 'NA';
    const min = (sex === 'Female')
        ? parameter.normalMinFemale
        : parameter.normalMinMale;
    const max = (sex === 'Female')
        ? parameter.normalMaxFemale
        : parameter.normalMaxMale;
    if (numericValue < min)
        return 'LOW';
    if (numericValue > max)
        return 'HIGH';
    return 'NORMAL';
}
//# sourceMappingURL=flagging.js.map
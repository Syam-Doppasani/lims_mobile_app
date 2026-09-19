export type ValueFlag = 'LOW' | 'HIGH' | 'NORMAL' | 'NA';

export interface Parameter {
  id: string;
  name: string;
  unit: string;
  normalMinMale: number;
  normalMaxMale: number;
  normalMinFemale: number;
  normalMaxFemale: number;
  inputType: 'number' | 'text';
}

export function computeFlag(
  rawValue: string,
  parameter: Parameter,
  sex: string
): ValueFlag {
  if (parameter.inputType === 'text') return 'NA';

  const numericValue = parseFloat(rawValue);
  if (isNaN(numericValue)) return 'NA';

  const min = (sex === 'Female')
    ? parameter.normalMinFemale
    : parameter.normalMinMale;
  const max = (sex === 'Female')
    ? parameter.normalMaxFemale
    : parameter.normalMaxMale;

  if (numericValue < min) return 'LOW';
  if (numericValue > max) return 'HIGH';
  return 'NORMAL';
}

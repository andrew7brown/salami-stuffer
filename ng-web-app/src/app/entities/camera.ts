
import {Resource} from 'ng2-hallelujah';
import { PhotoCapture } from './photo-capture'

export class Camera extends Resource {
	namedLocation: string;
	url: string;
	latitude: number;
	longitude: number;
	active: boolean;
	photos: PhotoCapture[];
};

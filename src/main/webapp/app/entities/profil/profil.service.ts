import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Profil } from './profil.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ProfilService {

    private resourceUrl = SERVER_API_URL + 'api/profils';

    constructor(private http: Http) { }

    create(profil: Profil): Observable<Profil> {
        const copy = this.convert(profil);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(profil: Profil): Observable<Profil> {
        const copy = this.convert(profil);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Profil> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to Profil.
     */
    private convertItemFromServer(json: any): Profil {
        const entity: Profil = Object.assign(new Profil(), json);
        return entity;
    }

    /**
     * Convert a Profil to a JSON which can be sent to the server.
     */
    private convert(profil: Profil): Profil {
        const copy: Profil = Object.assign({}, profil);
        return copy;
    }
}

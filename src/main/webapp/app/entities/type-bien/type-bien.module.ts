import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SergicSharedModule } from '../../shared';
import {
    TypeBienService,
    TypeBienPopupService,
    TypeBienComponent,
    TypeBienDetailComponent,
    TypeBienDialogComponent,
    TypeBienPopupComponent,
    TypeBienDeletePopupComponent,
    TypeBienDeleteDialogComponent,
    typeBienRoute,
    typeBienPopupRoute,
} from './';

const ENTITY_STATES = [
    ...typeBienRoute,
    ...typeBienPopupRoute,
];

@NgModule({
    imports: [
        SergicSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TypeBienComponent,
        TypeBienDetailComponent,
        TypeBienDialogComponent,
        TypeBienDeleteDialogComponent,
        TypeBienPopupComponent,
        TypeBienDeletePopupComponent,
    ],
    entryComponents: [
        TypeBienComponent,
        TypeBienDialogComponent,
        TypeBienPopupComponent,
        TypeBienDeleteDialogComponent,
        TypeBienDeletePopupComponent,
    ],
    providers: [
        TypeBienService,
        TypeBienPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SergicTypeBienModule {}

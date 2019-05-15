import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import { PatientApiGatewaySharedLibsModule, PatientApiGatewaySharedCommonModule, HasAnyAuthorityDirective } from './';

@NgModule({
    imports: [PatientApiGatewaySharedLibsModule, PatientApiGatewaySharedCommonModule],
    declarations: [HasAnyAuthorityDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    exports: [PatientApiGatewaySharedCommonModule, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PatientApiGatewaySharedModule {
    static forRoot() {
        return {
            ngModule: PatientApiGatewaySharedModule
        };
    }
}

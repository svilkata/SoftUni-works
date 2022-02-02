import { html, classMap } from "../lib.js";

export const input = (label, type, name, value = '', hasError) => {
        if (type == 'textarea') {
                return html`
                <label class=${classMap({ error: hasError })}><span>${label}</span>
                <textarea name=${name} .value=${value}></textarea></label>`;
        } else {
                return html`
                <label class=${classMap({ error: hasError })}><span>${label}</span><input type=${type} name=${name}
                                .value=${value}></label>`;
        }
};

/*
<label><span>Email</span><input type="text" name="email" .value=${values.email}></label>
<label><span>Display name</span><input type="text" name="username" .value=${values.username}></label>
<label><span>Password</span><input type="password" name="password" .value=${values.password}></label>
<label><span>Repeat Pass</span><input type="password" name="repass" .value=${values.repass}></label>*/
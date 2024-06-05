import React, { useState } from 'react';
import cl from './Switch.module.css';

const Switch = ({isActive, ...props}) => {
  return (
    <div className={isActive ? cl.switch + ' ' + cl.switchToggled : cl.switch} {...props}>
        <div className={cl.switchHandle}></div>
    </div>
  );
};

export default Switch;

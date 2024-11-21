import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import '@umijs/max';
import React from 'react';

const Footer: React.FC = () => {
  const defaultMessage = '玦尘';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'codeNav',
          title: 'CSDN',
          href: 'https://blog.csdn.net/weixin_74199893?spm=1000.2115.3001.5343',
          blankTarget: true,
        },
        {
          key: 'github',
          title: (
            <>
              <GithubOutlined /> 玦尘源码
            </>
          ),
          href: 'https://github.com/miahemu',
          blankTarget: true,
        },
      ]}
    />
  );
};
export default Footer;
